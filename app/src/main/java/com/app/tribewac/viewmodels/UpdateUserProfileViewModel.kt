package com.app.tribewac.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tribewac.base.BaseResult
import com.app.tribewac.base.ResultWrapper
import com.app.tribewac.data.models.specificuser.GetSpecificUserModel
import com.app.tribewac.data.models.updateuser.UpdateUserRequest
import com.app.tribewac.data.models.updateuser.UpdateUserResponse
import com.app.tribewac.data.repository.AppRepository
import kotlinx.coroutines.launch

class UpdateUserProfileViewModel @ViewModelInject constructor(private val repository: AppRepository) :
    ViewModel() {


    var _getSpecificUserDetailsData: MutableLiveData<BaseResult<GetSpecificUserModel>>? =
        MutableLiveData()

    val getSpecificUserDetailsData: LiveData<BaseResult<GetSpecificUserModel>>? =
        _getSpecificUserDetailsData


    var _getUpdateUserData: MutableLiveData<BaseResult<UpdateUserResponse>>? =
        MutableLiveData()

    val getUpdateUserData: LiveData<BaseResult<UpdateUserResponse>>? = _getUpdateUserData

    var userId=repository.getUserId()

    init {
        getSpecificUserDetails(userId)
    }


    fun getUpdateUser(userId: String, updateUser: UpdateUserRequest) {

        viewModelScope.launch {



                _getUpdateUserData?.postValue(BaseResult.loading())

                when (val response =
                    repository.getUpdateUser(userId, updateUser = updateUser)) {
                    is ResultWrapper.Success -> _getUpdateUserData?.postValue(
                        BaseResult.success(
                            response.data
                        )
                    )
                    is ResultWrapper.Failure -> _getUpdateUserData?.postValue(
                        BaseResult.error(
                            response.message
                        )
                    )
                }



        }
    }


   private fun getSpecificUserDetails(userId: String) {

        viewModelScope.launch {

            if(_getSpecificUserDetailsData?.value!=null){

              /*  val oldValue=_getSpecificUserDetailsData
                _getSpecificUserDetailsData=oldValue*/

            }else{
                _getSpecificUserDetailsData?.postValue(BaseResult.loading())

                when (val response =
                    repository.getSpecificUserDetails(userId)) {
                    is ResultWrapper.Success -> _getSpecificUserDetailsData?.postValue(
                        BaseResult.success(
                            response.data
                        )
                    )
                    is ResultWrapper.Failure -> _getSpecificUserDetailsData?.postValue(
                        BaseResult.error(
                            response.message
                        )
                    )
                }
            }



        }
    }


}