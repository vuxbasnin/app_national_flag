package com.base.basemvvm.data

import android.util.Log
import com.base.basemvvm.data.local.dao.NationalFlagDao
import com.base.basemvvm.data.model.response.flag.NationalFlagResponseItem
import com.base.basemvvm.data.network.data_source.DemoDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class DemoRepository @Inject constructor(private val demoDataSource: DemoDataSource) {
    fun getDemo() = flow {
        emit(demoDataSource.getDemo())
    }.flowOn(Dispatchers.IO)

    fun getListNationFlag() = flow {
        emit(demoDataSource.getListNationFlag())
    }.flowOn(Dispatchers.IO)

    fun getListNationFlagLocal() = flow {
        emit(demoDataSource.getListNationalFlagsFromLocal())
    }.flowOn(Dispatchers.IO)

    fun insertNationFlagLocal(data: NationalFlagResponseItem) = flow {
        emit(demoDataSource.insertNationalFlagToLocal(data))
    }.flowOn(Dispatchers.IO)
}