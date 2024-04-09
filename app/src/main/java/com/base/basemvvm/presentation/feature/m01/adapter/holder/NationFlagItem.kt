package com.base.basemvvm.presentation.feature.m01.adapter.holder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.base.basemvvm.core.utils.Utility
import com.base.basemvvm.data.model.response.flag.NationalFlagResponseItem
import com.base.basemvvm.databinding.LayoutItemNationFlagBinding
import com.base.basemvvm.presentation.my_interface.OnClickItemFlag

class NationFlagItem(private val binding: LayoutItemNationFlagBinding, val context: Context) :
    RecyclerView.ViewHolder(binding.root) {

    fun setData(nationFragItem: NationalFlagResponseItem, onClickItemFlag: OnClickItemFlag) {
        try {
            Utility.setImage(context, binding.imgNationalFlag, nationFragItem.flags?.png)
            binding.txtNameNational.text = nationFragItem.name?.common
            binding.root.setOnClickListener {
                onClickItemFlag.callback(OnClickItemFlag.TagFlag.ON_CLICK_ITEM_FLAG, nationFragItem)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}