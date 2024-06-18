package vbn.clean.nation_flag.presentation.feature.m02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import dagger.hilt.android.AndroidEntryPoint
import vbn.clean.nation_flag.core.utils.DeviceUtil
import vbn.clean.nation_flag.databinding.M02FragmentBinding
import vbn.clean.nation_flag.presentation.core.base.BaseFragment
import vbn.clean.nation_flag.presentation.core.widget.SpeedometerPreview

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