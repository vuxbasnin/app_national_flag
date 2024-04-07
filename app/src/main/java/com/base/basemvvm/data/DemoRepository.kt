package com.base.basemvvm.data

import com.base.basemvvm.data.network.data_source.DemoDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DemoRepository @Inject constructor(private val demoDataSource: DemoDataSource) {
    fun getDemo() = flow {
        emit(demoDataSource.getDemo())
    }.flowOn(Dispatchers.IO)

    fun getListNationFlag() = flow {
        emit(demoDataSource.getListNationFlag())
    }.flowOn(Dispatchers.IO)
}