package com.base.basemvvm.presentation.feature.m02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.base.basemvvm.core.utils.DeviceUtil
import com.base.basemvvm.databinding.M02FragmentBinding
import com.base.basemvvm.presentation.core.base.BaseFragment
import com.base.basemvvm.presentation.core.widget.SpeedometerPreview
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class M02Fragment : BaseFragment<M02FragmentBinding>(M02FragmentBinding::inflate) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            DeviceUtil.getInstance().getScreenWidthInDp(requireContext()).let {
                setContent {
                    SpeedometerPreview(it)
                }
            }
        }
    }
    override fun initView() {

    }

    override fun initObserver() {

    }

    override fun getData() {

    }

    override fun onClick(p0: View?) {

    }
}