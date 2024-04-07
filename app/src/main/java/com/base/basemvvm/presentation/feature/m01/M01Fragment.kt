package com.base.basemvvm.presentation.feature.m01

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.base.basemvvm.core.common.addDivider
import com.base.basemvvm.core.common.hide
import com.base.basemvvm.core.common.show
import com.base.basemvvm.core.utils.Utility
import com.base.basemvvm.data.model.response.flag.NationalFlagResponseItem
import com.base.basemvvm.databinding.M01FragmentBinding
import com.base.basemvvm.presentation.core.base.BaseFragment
import com.base.basemvvm.presentation.core.base.CommonState
import com.base.basemvvm.presentation.core.widget.OnScrollRecyclerview
import com.base.basemvvm.presentation.core.widget.SnapCenterListener
import com.base.basemvvm.presentation.feature.m01.adapter.M01Adapter
import dagger.hilt.android.AndroidEntryPoint
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
        m01Adapter = M01Adapter(requireContext())
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

    private fun setUpSearchView(){
        try {
            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
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
        if(!query.isNullOrBlank()) {
            val filteredList = ArrayList<NationalFlagResponseItem>()
            m01Adapter?.listData?.forEach {
                if (it.name.common.lowercase(Locale.ROOT).contains(query)) filteredList.add(it)
            }

            if (filteredList.isEmpty()){
                Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show()
                binding.rcvListData.hide()
            } else {
                m01Adapter?.setDataFiltered(filteredList)
            }
        } else {
            m01Adapter?.setDataFiltered(listDataDefault)
        }
    }

    override fun initObserver() {
        viewModel.m01State.observe(viewLifecycleOwner) {
            when(it){
                is CommonState.Success -> {
                    binding.swipeRefreshData.isRefreshing = false
                    binding.rcvListData.show()
                    m01Adapter?.setData(it.data, viewModel.currentPage)
                    listDataDefault.addAll(it.data)
                }

                is CommonState.Loading -> {

                }

                is CommonState.Fail -> {

                }

                else -> {

                }
            }
        }
    }

    override fun resetData() {
        binding.rcvListData.hide()
        binding.searchView.setQuery("", false)
        viewModel.reset()
        m01Adapter?.resetState()
        m01Adapter?.listData?.clear()
        listDataDefault.clear()
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