package com.app.tribewac.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tribewac.base.BaseResult
import com.app.tribewac.base.ResultWrapper
import com.app.tribewac.data.models.questionlists.QuestionListModelResponseItem
import com.app.tribewac.data.models.userinformation.UserInformationResponseModelItem
import com.app.tribewac.data.repository.AppRepository
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val repository: AppRepository
) : ViewModel() {


    val getUserInformationLiveData: MutableLiveData<BaseResult<List<UserInformationResponseModelItem>>> =
        MutableLiveData()

    val getQuestionsList: MutableLiveData<BaseResult<List<QuestionListModelResponseItem>>> =
        MutableLiveData()


    fun getUserToken(
        email: String
    ) {
        viewModelScope.launch {
            getUserInformationLiveData.postValue(BaseResult.loading())

            when (val response =
                repository.getUserInformation(email)) {
                is ResultWrapper.Success -> getUserInformationLiveData.postValue(
                    BaseResult.success(
                        response.data
                    )
                )
                is ResultWrapper.Failure -> getUserInformationLiveData.postValue(
                    BaseResult.error(
                        response.message
                    )
                )
            }

        }
    }


    fun getAllQuestions() {
        viewModelScope.launch {
            when (val response =
                repository.getQuestionsAllList()) {
                is ResultWrapper.Success -> getQuestionsList.postValue(
                    BaseResult.success(
                        response.data
                    )
                )
                is ResultWrapper.Failure -> getQuestionsList.postValue(
                    BaseResult.error(
                        response.message
                    )
                )
            }
        }
    }

}