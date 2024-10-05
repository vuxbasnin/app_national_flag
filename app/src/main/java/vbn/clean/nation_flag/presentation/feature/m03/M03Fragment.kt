package vbn.clean.nation_flag.presentation.feature.m03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import vbn.clean.nation_flag.data.model.response.chart.BobbleChart
import vbn.clean.nation_flag.data.model.response.chart.LineChartData
import vbn.clean.nation_flag.databinding.M03FragmentBinding
import vbn.clean.nation_flag.presentation.core.base.BaseFragment
import vbn.clean.nation_flag.presentation.core.base_chart.BobbleChartVolume
import vbn.clean.nation_flag.presentation.core.base_chart.LineChart

@AndroidEntryPoint
class M03Fragment : BaseFragment<M03FragmentBinding>(M03FragmentBinding::inflate) {

    val data = listOf(
        LineChartData(
            textTime = "2023",
            value = 120.36,
            year = 2023
        ),
        LineChartData(
            textTime = "2022",
            value = 142.77,
            year = 2022
        ),
        LineChartData(
            textTime = "2021",
            value = 150.87,
            year = 2021
        ),
        LineChartData(
            textTime = "2020",
            value = 91.28,
            year = 2020
        )
    )

    val listDataBobble = listOf(
        BobbleChart(name = "VPB", value = 100f, isMain = true),
        BobbleChart(name = "VCB", value = 120f, isMain = false),
        BobbleChart(name = "SHB", value = 50f, isMain = false),
        BobbleChart(name = "CTG", value = 70f, isMain = false),
        BobbleChart(name = "ABC", value = 30f, isMain = false),
        BobbleChart(name = "ABB", value = 34f, isMain = false),
        BobbleChart(name = "BID", value = 150f, isMain = false),
        BobbleChart(name = "LPB", value = 110f, isMain = false),
        BobbleChart(name = "ABB", value = 90f, isMain = false),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
//                Column(
//                    modifier = Modifier
//                        .background(Color.White)
//                ) {
//                    BobbleChartVolume(
//                        listDataBobble.sortedByDescending { it.value },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(300.dp)
//                            .align(
//                                Alignment.CenterHorizontally
//                            )
//                    )
//                }


                //bobble chart volume


//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    BobbleChartAnimation(
//                        listDataBobble,
//                        modifier = Modifier
//                            .padding(innerPadding)
//                    )
//                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    LineChart(
                        data = data.sortedBy { it.year },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .align(CenterHorizontally)
                    )
//                    Spacer(modifier = Modifier.height(40.dp))
//                    QuadLineChart(
//                        data = data,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(300.dp)
//                            .align(CenterHorizontally)
//                    )
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