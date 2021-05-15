package com.app.tribewac.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tribewac.base.BaseResult
import com.app.tribewac.base.ResultWrapper
import com.app.tribewac.data.models.questionimageupload.QuestionImageUploadModelResponse
import com.app.tribewac.data.models.questionresponsemodel.QuestionResponseModelResponse
import com.app.tribewac.data.repository.AppRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class AddQuestionsViewModel @ViewModelInject constructor(
    private val repository: AppRepository
) : ViewModel() {

    val getUploadQuestionImageUrl: MutableLiveData<BaseResult<QuestionImageUploadModelResponse>> =
        MutableLiveData()

    /** function to update user profile image in institution */
    fun getUpdateUserProfileImage(file: MultipartBody.Part) {
        viewModelScope.launch {

            getUploadQuestionImageUrl.postValue(BaseResult.loading())


            when (val response = repository.getImageUploadUrl(file)) {
                is ResultWrapper.Success -> getUploadQuestionImageUrl.postValue(
                    BaseResult.success(
                        response.data
                    )
                )
                is ResultWrapper.Failure -> getUploadQuestionImageUrl.postValue(
                    BaseResult.error(
                        response.message
                    )
                )
            }

        }
    }

    val createQuestionData: MutableLiveData<BaseResult<QuestionResponseModelResponse>> =
        MutableLiveData()

    fun createQuestionData(
        title: String,
        description: String,
        from: String,
        images: List<String>
    ) {
        viewModelScope.launch {

            createQuestionData.postValue(BaseResult.loading())


            when (val response = repository.createQuestionByUser(
                title = title,
                description = description,
                from = from,
                images = images
            )) {
                is ResultWrapper.Success -> createQuestionData.postValue(
                    BaseResult.success(
                        response.data
                    )
                )
                is ResultWrapper.Failure -> createQuestionData.postValue(
                    BaseResult.error(
                        response.message
                    )
                )
            }

        }
    }
}