package com.base.basemvvm.presentation.feature.m06_detail

import android.os.Bundle
import android.view.View
import com.base.basemvvm.core.utils.Utility
import com.base.basemvvm.data.model.response.flag.NationalFlagResponseItem
import com.base.basemvvm.databinding.M06DetailNationalFlagBinding
import com.base.basemvvm.presentation.NavigationManager
import com.base.basemvvm.presentation.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class M06DetailNationalFlagFragment :
    BaseFragment<M06DetailNationalFlagBinding>(M06DetailNationalFlagBinding::inflate) {
    private var nameNational: String? = null
    private var flag: String? = null

    companion object {
        private const val NAME_NATIONAL = "NAME_NATIONAL"
        private const val FLAG = "FLAG"
        fun getInstance(data: NationalFlagResponseItem?): M06DetailNationalFlagFragment {
            val args = Bundle()
            args.putString(NAME_NATIONAL, data?.name?.common)
            args.putString(FLAG, data?.flags?.png)
            val fragment = M06DetailNationalFlagFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initArgs() {
        super.initArgs()
        nameNational = arguments?.getString(NAME_NATIONAL)
        flag = arguments?.getString(FLAG)
    }

    override fun initView() {
        bindView()
    }

    private fun bindView() {
        Utility.setImage(requireContext(), binding.imgFlag, flag)
        binding.txtNationalName.text = nameNational
        binding.imgBackCustom.setOnClickListener {
            NavigationManager.getInstance().popBackStack()
        }
    }

    override fun initObserver() {

    }

    override fun getData() {

    }

    override fun onClick(v: View?) {

    }
}