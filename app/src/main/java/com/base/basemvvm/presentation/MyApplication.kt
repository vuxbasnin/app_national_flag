package com.base.basemvvm.presentation

import android.app.Application
import com.base.basemvvm.BuildConfig
import com.base.basemvvm.core.utils.LogsUtil
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //config, init sdk in layer application
        if (BuildConfig.DEBUG) {
            Timber.plant(LogsUtil())
        }
    }
}