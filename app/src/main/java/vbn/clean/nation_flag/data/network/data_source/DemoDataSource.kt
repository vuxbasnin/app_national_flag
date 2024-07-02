package vbn.clean.nation_flag.data.network.data_source

import android.content.Context
import vbn.clean.nation_flag.data.local.dao.NationalFlagDao
import vbn.clean.nation_flag.data.model.response.flag.NationalFlagResponseItem
import vbn.clean.nation_flag.data.network.DefaultRequest
import vbn.clean.nation_flag.data.network.service.DemoService
import javax.inject.Inject

class DemoDataSource @Inject constructor(
    private val demoService: DemoService,
    private val localServiceDao: NationalFlagDao,
    context: Context
) : BaseDataSource(context) {
    suspend fun getDemo() = safeApiCall {
        demoService.getDemo(DefaultRequest())
    }

    suspend fun getListNationFlag() = safeApiCall {
        demoService.getNationFlag()
    }

    suspend fun getListNationalFlagsFromLocal() = localServiceDao.getListNationalFlagsFromLocal()

    suspend fun insertNationalFlagToLocal(data: NationalFlagResponseItem) =
        localServiceDao.insert(data)

    suspend fun deleteNationalFlagLocal() = localServiceDao.deleteAll()
}