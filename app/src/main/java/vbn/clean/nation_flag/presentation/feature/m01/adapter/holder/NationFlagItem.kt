package vbn.clean.nation_flag.presentation.feature.m01.adapter.holder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import vbn.clean.nation_flag.core.utils.Utility
import vbn.clean.nation_flag.data.model.response.flag.NationalFlagResponseItem
import vbn.clean.nation_flag.databinding.LayoutItemNationFlagBinding
import vbn.clean.nation_flag.presentation.my_interface.OnClickItemFlag

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