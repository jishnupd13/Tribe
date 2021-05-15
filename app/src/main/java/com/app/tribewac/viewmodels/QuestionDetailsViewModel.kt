package com.app.tribewac.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tribewac.base.BaseResult
import com.app.tribewac.base.ResultWrapper
import com.app.tribewac.data.models.createanswermodel.CreatedAnswerModel
import com.app.tribewac.data.models.questionlists.QuestionListModelResponseItem
import com.app.tribewac.data.repository.AppRepository
import kotlinx.coroutines.launch

class QuestionDetailsViewModel @ViewModelInject constructor(
    private val repository: AppRepository
) : ViewModel() {


    val getLikeQuestionData: MutableLiveData<BaseResult<QuestionListModelResponseItem>> =
        MutableLiveData()

    val getUnlikeQuestionData: MutableLiveData<BaseResult<QuestionListModelResponseItem>> =
        MutableLiveData()

    val getQuestionDetails: MutableLiveData<BaseResult<QuestionListModelResponseItem>> =
        MutableLiveData()


    val createAnswerUsingQuestion: MutableLiveData<BaseResult<CreatedAnswerModel>> =
        MutableLiveData()


    fun createAnswerUsingQuestions(id: String,content:String) {

        viewModelScope.launch {
            when (val response =
                repository.createAnswer(id,content)) {
                is ResultWrapper.Success -> createAnswerUsingQuestion.postValue(
                    BaseResult.success(
                        response.data
                    )
                )
                is ResultWrapper.Failure -> createAnswerUsingQuestion.postValue(
                    BaseResult.error(
                        response.message
                    )
                )
            }
        }
    }




    fun getQuestionDetails(id: String) {

        viewModelScope.launch {
            getQuestionDetails.postValue(BaseResult.loading())

            when (val response =
                repository.getQuestionDetails(id)) {
                is ResultWrapper.Success -> getQuestionDetails.postValue(
                    BaseResult.success(
                        response.data
                    )
                )
                is ResultWrapper.Failure -> getQuestionDetails.postValue(
                    BaseResult.error(
                        response.message
                    )
                )
            }

        }
    }


    fun getLikeQuestion(id: String) {

        viewModelScope.launch {
            getLikeQuestionData.postValue(BaseResult.loading())

            when (val response =
                repository.getQuestionLike(id)) {
                is ResultWrapper.Success -> getLikeQuestionData.postValue(
                    BaseResult.success(
                        response.data
                    )
                )
                is ResultWrapper.Failure -> getLikeQuestionData.postValue(
                    BaseResult.error(
                        response.message
                    )
                )
            }

        }
    }

    fun getUnLikeQuestion(id: String) {

        viewModelScope.launch {
            getUnlikeQuestionData.postValue(BaseResult.loading())

            when (val response =
                repository.getQuestionUnLike(id)) {
                is ResultWrapper.Success -> getUnlikeQuestionData.postValue(
                    BaseResult.success(
                        response.data
                    )
                )
                is ResultWrapper.Failure -> getUnlikeQuestionData.postValue(
                    BaseResult.error(
                        response.message
                    )
                )
            }

        }
    }

}