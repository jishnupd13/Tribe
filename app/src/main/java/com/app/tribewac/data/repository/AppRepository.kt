package com.app.tribewac.data.repository

import com.app.tribewac.base.safeApiCall
import com.app.tribewac.data.local.PreferencesHandler
import com.app.tribewac.data.models.Post
import com.app.tribewac.network.ApiInterface
import dagger.Reusable
import okhttp3.MultipartBody
import javax.inject.Inject

/**
 * Created By antony on 6/4/2019.
 */
@Reusable
class AppRepository @Inject constructor(
    private val webService: ApiInterface,
//    private val appDao: AppDao,
    private val preferencesHandler: PreferencesHandler
) {

    suspend fun getPosts() = safeApiCall { webService.getPosts() }

    suspend fun getList(postRequest: Post) = safeApiCall { webService.getList(postRequest) }

    fun saveUserToken(token: String) {
        preferencesHandler.userToken = token
    }

    /** tribe create a user */
    suspend fun getUserTokenUsingEmail(
        grantType: String,
        clientId: String,
        clientSecret: String,
        email: String
    ) =
        safeApiCall {
            webService.getUserTokenUsingEmail(grantType, clientId, clientSecret, email)
        }

    /** tribe create a user */
    suspend fun registerTribeUser(
        userName: String,
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ) = safeApiCall {
        webService.getTokenUserRegister(userName, name, email, password, confirmPassword)
    }


    suspend fun getUserTokenUsingEmailAndPassword(
        grantType: String,
        clientId: String,
        clientSecret: String,
        email: String,
        password: String
    ) =
        safeApiCall {
            webService.getUserTokenUsingEmailAndPassword(
                grantType,
                clientId,
                clientSecret,
                email,
                password
            )
        }

    suspend fun getUserInformation(
        email: String
    ) = safeApiCall {
        webService.getUserDetails(page = 1, limit = 1, sort = "createdAt.desc", email = email)
    }

    suspend fun getImageUploadUrl(part: MultipartBody.Part) = safeApiCall {
        webService.getImageUploadUrl(part)
    }

    suspend fun createQuestionByUser(
        title: String,
        description: String,
        from: String,
        images: List<String>
    ) = safeApiCall {
        webService.createQuestion(
            title = title,
            description = description,
            anonymous = true,
            from = from,
            images = images
        )
    }

    suspend fun getQuestionsAllList(
    ) = safeApiCall {
        webService.getQuestionList(
            page = 1,
            limit = 30,
            sort = "createdAt.desc"
        )
    }

    suspend fun getQuestionDetails(id:String)= safeApiCall {
        webService.getQuestionDetails(id)
    }

    suspend fun getQuestionLike(id:String)= safeApiCall {
        webService.getQuestionLike(id)
    }

    suspend fun getQuestionUnLike(id:String)= safeApiCall {
        webService.getQuestionUnLike(id)
    }

    suspend fun createAnswer(id:String,content:String)= safeApiCall {
        webService.createAnswer(id,content,false,"new")
    }

}