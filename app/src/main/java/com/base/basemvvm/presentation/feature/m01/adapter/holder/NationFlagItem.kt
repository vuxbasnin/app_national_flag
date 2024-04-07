package com.base.basemvvm.presentation.feature.m01.adapter.holder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.base.basemvvm.core.utils.Utility
import com.base.basemvvm.data.model.response.flag.NationalFlagResponseItem
import com.base.basemvvm.databinding.LayoutItemNationFlagBinding

class NationFlagItem(private val binding: LayoutItemNationFlagBinding, val context: Context) :
    RecyclerView.ViewHolder(binding.root) {

    fun setData(nationFragItem: NationalFlagResponseItem) {
        try {
            Utility.setImage(context, binding.imgNationalFlag, nationFragItem.flags.png)
            binding.txtNameNational.text = nationFragItem.name.common
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}