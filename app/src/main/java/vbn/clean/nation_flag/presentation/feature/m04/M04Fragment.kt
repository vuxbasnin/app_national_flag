package vbn.clean.nation_flag.presentation.feature.m04

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import vbn.clean.nation_flag.R
import vbn.clean.nation_flag.data.model.response.chart.candlestickData
import vbn.clean.nation_flag.data.model.response.chart.json
import vbn.clean.nation_flag.data.model.response.chart.parseCandlestickData
import vbn.clean.nation_flag.databinding.M04FragmentBinding
import vbn.clean.nation_flag.presentation.core.base.BaseFragment
import vbn.clean.nation_flag.presentation.core.base_chart.CandleChart
import vbn.clean.nation_flag.presentation.core.base_chart.LineChart
import vbn.clean.nation_flag.presentation.core.base_chart.drawVolumes

class M04Fragment : BaseFragment<M04FragmentBinding>(M04FragmentBinding::inflate) {
    val data = listOf(
        Pair("", 79.0),
        Pair("04/2020", 75.0),
        Pair("01/2021", 99.0),
        Pair("02/2021", 73.0),
        Pair("03/2021", 75.0),
        Pair("", 99.0),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    CandleChart(
                        listData = parseCandlestickData(json),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .align(CenterHorizontally)
                    )
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