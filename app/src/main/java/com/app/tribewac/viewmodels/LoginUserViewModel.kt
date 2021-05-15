package com.app.tribewac.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tribewac.base.BaseResult
import com.app.tribewac.base.ResultWrapper
import com.app.tribewac.data.models.getmaintoken.GetAppMainTokenModel
import com.app.tribewac.data.repository.AppRepository
import kotlinx.coroutines.launch

class LoginUserViewModel   @ViewModelInject constructor(
    private val repository: AppRepository
) : ViewModel() {


    var getUserTokenData: MutableLiveData<BaseResult<GetAppMainTokenModel>> =
        MutableLiveData()

    fun getUserToken( grantType: String,
                      clientId: String,
                      clientSecret: String,
                      email: String, password:String) {
        viewModelScope.launch {


            getUserTokenData.postValue(BaseResult.loading())

            when (val response = repository.getUserTokenUsingEmailAndPassword(grantType, clientId, clientSecret, email,password)) {
                is ResultWrapper.Success -> getUserTokenData.postValue(
                    BaseResult.success(
                        response.data
                    )
                )
                is ResultWrapper.Failure -> getUserTokenData.postValue(
                    BaseResult.error(
                        response.message
                    )
                )
            }

        }
    }
}