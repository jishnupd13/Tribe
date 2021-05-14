package com.app.tribewac.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tribewac.base.BaseResult
import com.app.tribewac.base.ResultWrapper
import com.app.tribewac.data.models.registeruser.RegisterUserResponseModel
import com.app.tribewac.data.repository.AppRepository
import kotlinx.coroutines.launch

class RegisterUserViewModel @ViewModelInject constructor(
    private val repository: AppRepository
) : ViewModel() {

    var registerUserData: MutableLiveData<BaseResult<RegisterUserResponseModel>> =
        MutableLiveData()

    fun registerUser(
        userName: String,
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {

        viewModelScope.launch {

            registerUserData.postValue(BaseResult.loading())

            when (val response =
                repository.registerTribeUser(userName, name, email, password, confirmPassword)) {
                is ResultWrapper.Success -> registerUserData.postValue(
                    BaseResult.success(
                        response.data
                    )
                )
                is ResultWrapper.Failure -> registerUserData.postValue(
                    BaseResult.error(
                        response.message
                    )
                )
            }


        }

    }
}