package com.antony.mybasepackage.rxbus

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Created By antony on 7/31/2019.
 */
object RxBus {
    private val publisher = MutableSharedFlow<Any>()

    suspend fun publish(event: Any) {
        publisher.emit(event)
    }

    fun <T> listen(eventType: Class<T>) = publisher.asSharedFlow()
}
