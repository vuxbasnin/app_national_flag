package com.base.basemvvm.presentation

import android.app.Application
import com.base.basemvvm.data.DemoRepository
import com.base.basemvvm.data.local.room_database.NationalFlagRoomDatabase
import com.base.basemvvm.data.network.data_source.DemoDataSource
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //config, init sdk in layer application
    }
}