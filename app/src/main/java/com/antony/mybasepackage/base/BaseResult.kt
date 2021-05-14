package com.antony.mybasepackage.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.antony.mybasepackage.utils.InternetUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import retrofit2.Response
import java.net.UnknownHostException

/**
 * Created By antony on 9/9/2019.
 */
data class BaseResult<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): BaseResult<T> {
            return BaseResult(Status.SUCCESS, data, "")
        }

        fun <T> error(message: String, data: T? = null): BaseResult<T> {
            return BaseResult(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): BaseResult<T> {
            return BaseResult(Status.LOADING, data, "")
        }
    }
}

/**
 * The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [BaseResult.Status.SUCCESS] - with data from database
 * [BaseResult.Status.ERROR] - if error has occurred from any source
 * [BaseResult.Status.LOADING]
 */
fun <A> resultLiveData(
    networkCall: suspend () -> Response<A>,
    dbInsert: (suspend (A) -> Unit)? = null,
    dbRetrieve: (suspend () -> A)? = null
): LiveData<BaseResult<A>> =
    liveData(Dispatchers.IO) {
        emit(BaseResult.loading())
        if (InternetUtils.isInternetAvailable) {
            val responseStatus = networkCall.invoke()
            if (responseStatus.isSuccessful && responseStatus.body() != null) {
                emit(BaseResult.success(responseStatus.body()!!))
                dbInsert?.invoke(responseStatus.body()!!)
            } else {
                val dbResponse = dbRetrieve?.invoke()
                if (dbResponse != null) {
                    emit(BaseResult.success(dbResponse))
                } else {
                    emit(BaseResult.error<A>(responseStatus.message()))
                }
            }
        } else {
            val dbResponse = dbRetrieve?.invoke()
            if (dbResponse != null) {
                emit(BaseResult.success(dbResponse))
            } else {
                emit(BaseResult.error<A>("No internet"))
            }
        }
    }

sealed class ResultWrapper<out T> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Failure(val message: String, val errorType: ErrorType) : ResultWrapper<Nothing>()
}

enum class ErrorType {
    NETWORK_ERROR,
    JSON_PARSE_ERROR,
    UNKNOWN
}

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): ResultWrapper<T> {
    return try {
        val response = apiCall.invoke()
        if (response.isSuccessful && response.body() != null) {
            ResultWrapper.Success(response.body()!!)
        } else {
            ResultWrapper.Failure(response.message() ?: "", ErrorType.UNKNOWN)
        }
    } catch (throwable: Throwable) {
        when (throwable) {
            is JSONException -> ResultWrapper.Failure(
                throwable.message ?: "",
                ErrorType.JSON_PARSE_ERROR
            )
            is UnknownHostException -> ResultWrapper.Failure(
                "No internet",
                ErrorType.NETWORK_ERROR
            )
            else -> ResultWrapper.Failure("", ErrorType.UNKNOWN)
        }
    }
}