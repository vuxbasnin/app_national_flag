package com.base.basemvvm.presentation.feature.m01

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base.basemvvm.data.DemoRepository
import com.base.basemvvm.data.model.response.flag.NationalFlagResponseItem
import com.base.basemvvm.presentation.core.base.BaseViewModel
import com.base.basemvvm.presentation.core.base.CommonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
            m01Repository.getListNationFlag().collect {
                if (it.data != null) {
                    val result = ArrayList(prevList ?: listOf())
                    result.addAll(it.data ?: listOf())
                    if (result.isNotEmpty()) {
                        _m01State.value = CommonState.Success(result)
                    } else {
                        _m01State.value = CommonState.Fail(it.message)
                    }
                } else {
                    _m01State.value = CommonState.Fail(it.message)
                }
            }
        }
    }
}