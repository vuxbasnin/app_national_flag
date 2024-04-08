package com.base.basemvvm.presentation.feature.m01

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base.basemvvm.core.utils.InternetUtil
import com.base.basemvvm.data.DemoRepository
import com.base.basemvvm.data.local.room_database.NationalFlagRoomDatabase
import com.base.basemvvm.data.model.response.flag.NationalFlagResponseItem
import com.base.basemvvm.presentation.core.base.BaseViewModel
import com.base.basemvvm.presentation.core.base.CommonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class M01ViewModel @Inject constructor(private val m01Repository: DemoRepository) :
    BaseViewModel() {
    val _m01State = MutableLiveData<CommonState<ArrayList<NationalFlagResponseItem>>>()
    val m01State get() = _m01State

    fun reset() {
        getListNationFlag()
    }

    fun getListNationFlag(prevList: ArrayList<NationalFlagResponseItem>? = null) {
        viewModelScope.launch {
            _m01State.value = CommonState.Loading
            if (InternetUtil.isNetworkAvailable()) {
                Log.d("NINVB", "Get data from api")
                m01Repository.getListNationFlag().collect {
                    if (it.data != null) {
                        val result = ArrayList(prevList ?: listOf())
                        result.addAll(it.data)
                        if (result.isNotEmpty()) {
                            _m01State.value = CommonState.Success(result)
                            result.forEach { item ->
                                insertNationalFlagToDatabase(item).collect{
                                    if (it > -1) {
                                        Log.d("NINVB", "Insert success")
                                    } else
                                        Log.d("NINVB", "Insert fail")
                                }
                            }
                        } else {
                            _m01State.value = CommonState.Fail(it.message)
                        }
                    } else {
                        _m01State.value = CommonState.Fail(it.message)
                    }
                }
            } else {
                Log.d("NINVB", "Get data from local")
                m01Repository.getListNationFlagLocal().collect{
                    _m01State.value = CommonState.Success(it)
                }
            }
        }
    }

    private suspend fun insertNationalFlagToDatabase(data: NationalFlagResponseItem) = withContext(Dispatchers.IO) {
            m01Repository.insertNationFlagLocal(data)
        }
}