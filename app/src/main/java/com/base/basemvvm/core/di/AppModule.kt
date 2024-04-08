package com.base.basemvvm.core.di

import android.app.Application
import android.content.Context
import com.base.basemvvm.data.local.room_database.NationalFlagRoomDatabase
import com.base.basemvvm.presentation.MyApplication
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Scope
import javax.inject.Singleton

/**
 * Here are the dependencies which will be injected by hilt
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    //Gson for converting JSON String to Java Objects
    @Provides
    fun providerGson(): Gson = GsonBuilder().setLenient().create()

    //Context
    @Provides
    @Singleton
    fun providerContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun providerApp(): MyApplication {
        return MyApplication()
    }

    @Provides
    fun providerDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }

    @Singleton
    @Provides
    fun provideNationalFlagRoomDatabase(
        context: Context,
        coroutineScope: CoroutineScope
    ): NationalFlagRoomDatabase {
        return NationalFlagRoomDatabase.getDatabase(context, coroutineScope)
    }
}