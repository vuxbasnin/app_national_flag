package vbn.clean.nation_flag.presentation.feature.m01

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import vbn.clean.nation_flag.core.common.addDivider
import vbn.clean.nation_flag.core.common.hide
import vbn.clean.nation_flag.core.common.show
import vbn.clean.nation_flag.core.utils.Utility
import vbn.clean.nation_flag.data.model.response.flag.NationalFlagResponseItem
import vbn.clean.nation_flag.databinding.M01FragmentBinding
import vbn.clean.nation_flag.presentation.NavigationManager
import vbn.clean.nation_flag.presentation.core.base.BaseFragment
import vbn.clean.nation_flag.presentation.core.base.CommonState
import vbn.clean.nation_flag.presentation.core.widget.OnScrollRecyclerview
import vbn.clean.nation_flag.presentation.core.widget.SnapCenterListener
import vbn.clean.nation_flag.presentation.feature.m01.adapter.M01Adapter
import vbn.clean.nation_flag.presentation.feature.m06_detail.M06DetailNationalFlagFragment
import vbn.clean.nation_flag.presentation.my_interface.OnClickItemFlag
import java.util.Locale

@AndroidEntryPoint
class M01Fragment : BaseFragment<M01FragmentBinding>(M01FragmentBinding::inflate),
    OnScrollRecyclerview {
    private val viewModel: M01ViewModel by viewModels()
    private var m01Adapter: M01Adapter? = null
    private val listDataDefault: ArrayList<NationalFlagResponseItem> = arrayListOf()

    override fun initView() {
        setupRcv()
    }

    private fun setupRcv() {
        binding.swipeRefreshData.isRefreshing = true
        binding.rcvListData.layoutManager = Utility.getLayoutVertical(context)
        m01Adapter = M01Adapter(requireContext(), onClickItemFlag)
        m01Adapter?.setUpLoadMore(binding.rcvListData) {
            viewModel.getListNationFlag(m01Adapter?.listData)
        }
        binding.rcvListData.adapter = m01Adapter
        val snapCenterListener = SnapCenterListener()
        snapCenterListener.setSmoothScroll(false)
        snapCenterListener.setOnScrollRecyclerview(this)
        binding.rcvListData.addOnScrollListener(snapCenterListener)
        binding.swipeRefreshData.setOnRefreshListener {
            binding.swipeRefreshData.isRefreshing = true
            resetData()
        }
        binding.rcvListData.addDivider()
        setUpSearchView()
    }

    private fun setUpSearchView() {
        try {
            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    filterList(newText)
                    return true
                }

            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun filterList(query: String?) {
        binding.rcvListData.show()
        if (!query.isNullOrBlank()) {
            val filteredList = ArrayList<NationalFlagResponseItem>()
            m01Adapter?.listData?.forEach {
                if (it.name?.common?.lowercase(Locale.ROOT)
                        ?.contains(query) == true
                ) filteredList.add(it)
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show()
                binding.rcvListData.hide()
            } else {
                m01Adapter?.setDataFiltered(filteredList)
            }
        } else {
            m01Adapter?.setDataFiltered(listDataDefault)
        }
    }

    private val onClickItemFlag = object : OnClickItemFlag {
        override fun callback(tag: OnClickItemFlag.TagFlag, data: Any?) {
            when(tag) {
                OnClickItemFlag.TagFlag.ON_CLICK_ITEM_FLAG -> {
                    val result = data as? NationalFlagResponseItem
                    NavigationManager.getInstance().openFragment(
                        M06DetailNationalFlagFragment.getInstance(result)
                    )
                }
            }
        }
    }

    override fun initObserver() {
        viewModel.m01State.observe(viewLifecycleOwner) {
            when (it) {
                is CommonState.Success -> {
                    binding.swipeRefreshData.isRefreshing = false
                    binding.rcvListData.show()
                    m01Adapter?.setData(it.data, viewModel.currentPage)
                    listDataDefault.addAll(it.data)
                }

                is CommonState.Loading -> {

                }

                is CommonState.Fail -> {
                    Toast.makeText(requireContext(), "No data!", Toast.LENGTH_SHORT).show()
                }

                else -> {

                }
            }
        }
    }

    override fun resetData() {
        binding.rcvListData.hide()
        binding.searchView.setQuery("", false)
        m01Adapter?.resetState()
        listDataDefault.clear()
        viewModel.reset()
    }

    override fun getData() {
        viewModel.reset()
    }

    override fun onClick(p0: View?) {

    }

    override fun onScrolled(position: Int) {

    }

    override fun onStartScroll(position: Int) {

    }
}