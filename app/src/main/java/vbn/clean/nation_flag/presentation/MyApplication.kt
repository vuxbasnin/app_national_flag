package vbn.clean.nation_flag.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import vbn.clean.nation_flag.core.utils.LogsUtil

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //config, init sdk in layer application
        if (vbn.clean.nation_flag.BuildConfig.DEBUG) {
            Timber.plant(LogsUtil())
        }
    }
}