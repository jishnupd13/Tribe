package com.antony.mybasepackage.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

object InternetUtils : DefaultLifecycleObserver {

    private var _isInternetAvailable = false
    private var isCallbackInitialised = false
    private lateinit var context: Context
    private val cm by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    fun registerNetworkCallback(context: Context, owner: LifecycleOwner) {
        this.context = context
        owner.lifecycle.addObserver(this)
    }

    val isInternetAvailable: Boolean
        get() {
            return if (!isCallbackInitialised) {
                throw Throwable("Callback not intialized")
            } else {
                _isInternetAvailable
            }
        }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _isInternetAvailable = true
        }

        override fun onLost(network: Network) {
            _isInternetAvailable = false
        }
    }

    private fun initCallback() {
        cm.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
    }

    override fun onStop(owner: LifecycleOwner) {
        cm.unregisterNetworkCallback(networkCallback)
        _isInternetAvailable = false
        isCallbackInitialised = false
        super.onStop(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        initCallback()
        isCallbackInitialised = true
    }
}