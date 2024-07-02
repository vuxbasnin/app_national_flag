package vbn.clean.nation_flag.presentation.feature.m03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import vbn.clean.nation_flag.data.model.response.chart.BobbleChart
import vbn.clean.nation_flag.databinding.M03FragmentBinding
import vbn.clean.nation_flag.presentation.core.base.BaseFragment
import vbn.clean.nation_flag.presentation.core.base_chart.BobbleChartVolume

@AndroidEntryPoint
class M03Fragment : BaseFragment<M03FragmentBinding>(M03FragmentBinding::inflate) {

    val data = listOf(
        Pair("", 79.0),
        Pair("04/2020", 75.0),
        Pair("01/2021", 99.0),
        Pair("02/2021", 73.0),
        Pair("03/2021", 75.0),
        Pair("02/2021", 73.0),
        Pair("03/2021", 75.0),
        Pair("", 99.0),
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
                Column(
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    BobbleChartVolume(
                        listDataBobble.sortedByDescending { it.value },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .align(
                                Alignment.CenterHorizontally
                            )
                    )
                }


                //bobble chart volume


//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    BobbleChartAnimation(
//                        listDataBobble,
//                        modifier = Modifier
//                            .padding(innerPadding)
//                    )
//                }

//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.White)
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    LineChart(
//                        data = data,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(300.dp)
//                            .align(CenterHorizontally)
//                    )
//                    Spacer(modifier = Modifier.height(40.dp))
//                    QuadLineChart(
//                        data = data,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(300.dp)
//                            .align(CenterHorizontally)
//                    )
//                }
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