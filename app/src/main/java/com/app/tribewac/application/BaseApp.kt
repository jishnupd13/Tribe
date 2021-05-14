package com.app.tribewac.application

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.app.tribewac.utils.InternetUtils.registerNetworkCallback
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        registerNetworkCallback(this, ProcessLifecycleOwner.get())
    }
}