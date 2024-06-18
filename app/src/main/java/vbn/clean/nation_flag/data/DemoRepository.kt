package vbn.clean.nation_flag.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import vbn.clean.nation_flag.data.model.response.flag.NationalFlagResponseItem
import vbn.clean.nation_flag.data.network.data_source.DemoDataSource
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