package com.antony.mybasepackage.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragViewBinder<T : ViewDataBinding>(val viewBinder: (Fragment) -> T) :
    ReadOnlyProperty<Fragment, T> {
    private var _value: T? = null

    @MainThread
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        check(Looper.myLooper() == Looper.getMainLooper())
        return if (_value != null)
            _value!!
        else {
            thisRef.viewLifecycleOwner.lifecycle.addObserver(BindingLifecycleObserver())
            _value = viewBinder(thisRef)
            _value!!
        }
    }

    private inner class BindingLifecycleObserver : DefaultLifecycleObserver {

        private val mainHandler = Handler(Looper.getMainLooper())

        @MainThread
        override fun onDestroy(owner: LifecycleOwner) {
            owner.lifecycle.removeObserver(this)
            mainHandler.post {
                _value = null
            }
        }
    }
}