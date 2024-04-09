package com.base.basemvvm.presentation.feature.m01.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.basemvvm.R
import com.base.basemvvm.core.common.BaseInfinityLoadAdapter
import com.base.basemvvm.data.model.response.flag.NationalFlagResponseItem
import com.base.basemvvm.databinding.LayoutItemNationFlagBinding
import com.base.basemvvm.presentation.core.widget.FooterHolder
import com.base.basemvvm.presentation.feature.m01.adapter.holder.NationFlagItem
import com.base.basemvvm.presentation.my_interface.OnClickItemFlag

class M01Adapter(private val context: Context, private val onClickItemFlag: OnClickItemFlag) :
    BaseInfinityLoadAdapter<NationalFlagResponseItem>(context) {
    companion object {
        const val TYPE = 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataFiltered(listDataFiltered: ArrayList<NationalFlagResponseItem>){
        listData.clear()
        listData.addAll(listDataFiltered)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder1(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE -> NationFlagItem(
                LayoutItemNationFlagBinding.inflate(LayoutInflater.from(context), parent, false),
                context
            )

            else -> FooterHolder(
                LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false), context
            )
        }
    }

    override fun onBindViewHolder1(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NationFlagItem -> {
                holder.setData(listData[position], onClickItemFlag)
            }
        }
    }

    override fun getItemViewType1(position: Int): Int {
        return 0
    }
}