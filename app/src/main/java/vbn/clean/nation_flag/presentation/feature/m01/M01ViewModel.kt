package vbn.clean.nation_flag.presentation.feature.m01

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import vbn.clean.nation_flag.core.utils.InternetUtil
import vbn.clean.nation_flag.data.DemoRepository
import vbn.clean.nation_flag.data.model.response.flag.NationalFlagResponseItem
import vbn.clean.nation_flag.presentation.core.base.BaseViewModel
import vbn.clean.nation_flag.presentation.core.base.CommonState
import javax.inject.Inject

@HiltViewModel
class M01ViewModel @Inject constructor(private val m01Repository: DemoRepository, private val context: Context) :
    BaseViewModel() {
    private var isUpdateData = true    //flag to check update data, current is always add data to database
    private val _m01State = MutableLiveData<CommonState<ArrayList<NationalFlagResponseItem>>>()
    val m01State get() = _m01State

    fun reset() {
        getListNationFlag()
    }

    fun getListNationFlag(prevList: ArrayList<NationalFlagResponseItem>? = null) {
        viewModelScope.launch {
            _m01State.value = CommonState.Loading
            if (InternetUtil.isNetworkConnected(context)) {
                Timber.d("M01ViewModel => Get data from api")
                m01Repository.getListNationFlag().collect {
                    if (it.data != null) {
                        val result = ArrayList(prevList ?: listOf())
                        result.addAll(it.data)
                        if (result.isNotEmpty()) {
                            _m01State.value = CommonState.Success(result)
                            if (isUpdateData) {
                                result.forEach { item ->
                                    insertNationalFlagToDatabase(item).collect {
                                        if (it > -1) {
                                            Timber.d("M01ViewModel => Insert success")
                                        } else
                                            Timber.d("M01ViewModel => Insert fail")
                                    }
                                }
                                isUpdateData = false
                            }
                        } else {
                            _m01State.value = CommonState.Fail(it.message)
                        }
                    } else {
                        _m01State.value = CommonState.Fail(it.message)
                    }
                }
            } else {
                Timber.d("M01ViewModel => Get data from local")
                m01Repository.getListNationFlagLocal().collect {
                    _m01State.value = CommonState.Success(it)
                }
            }
        }
    }

    private suspend fun insertNationalFlagToDatabase(data: NationalFlagResponseItem) =
        withContext(Dispatchers.IO) {
            m01Repository.insertNationFlagLocal(data)
        }
}