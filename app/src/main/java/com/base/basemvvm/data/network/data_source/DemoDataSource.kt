package com.base.basemvvm.data.network.data_source

import android.content.Context
import com.base.basemvvm.data.network.DefaultRequest
import com.base.basemvvm.data.network.service.DemoService
import javax.inject.Inject

class DemoDataSource @Inject constructor(
    private val demoService: DemoService,
    context: Context
): BaseDataSource(context) {
    suspend fun getDemo() = safeApiCall{
        demoService.getDemo(DefaultRequest())
    }

    suspend fun getListNationFlag() = safeApiCall {
        demoService.getNationFlag()
    }
}