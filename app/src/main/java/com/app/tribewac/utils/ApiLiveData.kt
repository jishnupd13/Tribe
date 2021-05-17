package com.app.tribewac.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.app.tribewac.base.BaseResult

class ApiLiveData<T>(
    private val shouldClearBeforeObserving: Boolean = false,
    private val func: (() -> Unit)? = null
) : LiveData<BaseResult<T>>() {

    fun loading(shouldOverrideLoading: Boolean = false) {

        if (value == null || shouldOverrideLoading) {
            value = BaseResult.loading()
        }
    }

    fun success(data: T) {
        postValue(BaseResult.success(data))
    }

    fun error(msg: String) {
        postValue(BaseResult.error(msg))
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in BaseResult<T>>) {
        func?.invoke()
        if (shouldClearBeforeObserving) value = null
        super.observe(owner, observer)
    }

    fun observe(owner: LifecycleOwner, observer: CustomObserver<T>) {
        func?.invoke()
        if (shouldClearBeforeObserving) value = null
        super.observe(owner, observer)
    }
}

interface CustomObserver<X> : Observer<BaseResult<X>> {
    override fun onChanged(t: BaseResult<X>?) {
        t?.let {
            when (it.status) {
                BaseResult.Status.LOADING -> {
                    onLoading()
                }
                BaseResult.Status.SUCCESS -> {
                    it.data?.let(this::onSuccess)
                }
                BaseResult.Status.ERROR -> {
                    onError(it.message ?: "")
                }
            }
        }
    }

    fun onLoading()
    fun onError(msg: String)
    fun onSuccess(data: X)
}