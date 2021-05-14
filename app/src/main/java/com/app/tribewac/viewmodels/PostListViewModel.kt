package com.app.tribewac.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tribewac.base.BaseResult
import com.app.tribewac.base.ResultWrapper
import com.app.tribewac.data.models.Post
import com.app.tribewac.data.repository.AppRepository
import kotlinx.coroutines.launch


class PostListViewModel @ViewModelInject constructor(
    private val repository: AppRepository
) : ViewModel() {
    private var _sample: MutableLiveData<BaseResult<List<Post>>>? = null
    val sample: LiveData<BaseResult<List<Post>>>
        get() {
            if (_sample == null) _sample = MutableLiveData()
            getPosts()
            return _sample!!
        }

    private fun getPosts() {
        viewModelScope.launch {
            _sample?.value = BaseResult.loading()
            // Retrieve from API
            when (val response = repository.getPosts()) {
                is ResultWrapper.Success -> _sample?.value = BaseResult.success(response.data)
                is ResultWrapper.Failure -> _sample?.value = BaseResult.error(response.message)
            }
        }
    }

    fun getList() {
        viewModelScope.launch {
            _sample?.value = BaseResult.loading()
            // Retrieve from API
            val request = Post(1, 1, "", "")
            when (val response = repository.getList(request)) {
                is ResultWrapper.Success -> _sample?.value = BaseResult.success(response.data)
                is ResultWrapper.Failure -> _sample?.value = BaseResult.error(response.message)
            }
        }
    }
}