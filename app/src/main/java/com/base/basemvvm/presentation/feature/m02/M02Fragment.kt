package com.base.basemvvm.presentation.feature.m02

import android.view.View
import com.base.basemvvm.R
import com.base.basemvvm.databinding.M02FragmentBinding
import com.base.basemvvm.presentation.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class M02Fragment: BaseFragment<M02FragmentBinding>(M02FragmentBinding::inflate) {
    override fun initView() {
        binding.semiCycle.setUpUI(R.color.primary)
        binding.btnChangeProgress.setOnClickListener {
            val progress = Random.nextInt(0, 100)
            binding.tvProgress.text = "$progress %"
            binding.semiCycle.setProcess(progress.toFloat()/100f)
        }
    }

    override fun initObserver() {
    }

    override fun getData() {
    }

    override fun onClick(p0: View?) {

    }
}