package com.antony.mybasepackage.application

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.antony.mybasepackage.utils.InternetUtils.registerNetworkCallback
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