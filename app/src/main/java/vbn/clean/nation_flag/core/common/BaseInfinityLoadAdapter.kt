package vbn.clean.nation_flag.core.common

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import vbn.clean.nation_flag.databinding.ItemLoadingBinding
import vbn.clean.nation_flag.presentation.core.widget.EndlessRecyclerViewScrollListener

abstract class BaseInfinityLoadAdapter<T>(val mContext: Context) : RecyclerView.Adapter<ViewHolder>() {
    companion object {
        const val LOADING = Int.MAX_VALUE
    }

    var currentPage: Int = 1
    var listData = ArrayList<T>()
    private var isVisibleLoading = false
    var scroll: EndlessRecyclerViewScrollListener? = null
    abstract fun onCreateViewHolder1(parent: ViewGroup, viewType: Int): ViewHolder
    abstract fun onBindViewHolder1(holder: ViewHolder, position: Int)
    abstract fun getItemViewType1(position: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == LOADING)
            LoadingHolder(ItemLoadingBinding.inflate(LayoutInflater.from(mContext), parent, false))
        else
            onCreateViewHolder1(parent, viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is LoadingHolder)
            holder.visibleLoading(isVisibleLoading)
        else
            onBindViewHolder1(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == listData.size) LOADING else getItemViewType1(position)
    }

    fun setUpLoadMore(recyclerView: RecyclerView, onLoadMore: () -> Unit) {
        scroll = object : EndlessRecyclerViewScrollListener(recyclerView.layoutManager, currentPage) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (page < this@BaseInfinityLoadAdapter.currentPage) {
                    onLoadMore()
                    visibleLoadMore(true)
                }
            }

            override fun currentPosition(position: Int) {
            }

            override fun getDy(dy: Int) {
            }
        }.apply {
            recyclerView.addOnScrollListener(this)
        }

    }

    fun resetState() {
        scroll?.resetState()
    }

    fun clearData() {
        listData.clear()
        notifyDataSetChanged()
    }

    fun visibleLoadMore(isVisible: Boolean) {
        isVisibleLoading = isVisible
        notifyItemChanged(listData.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<T>, currentPage: Int = 1) {
        scroll?.isFull = listData.size == data.size
        this.currentPage = currentPage
        val positionPrev = listData.size
        listData.clear()
        listData.addAll(data)
        val itemCount = data.size - positionPrev
        visibleLoadMore(false)
        if (itemCount <= 0 || positionPrev == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(positionPrev, itemCount)
        }
    }

    override fun getItemCount() = listData.size + if (listData.isNotEmpty() && scroll?.isFull != true) 1 else 0

    class LoadingHolder(private val itemLoadingBinding: ItemLoadingBinding) : ViewHolder(itemLoadingBinding.root) {
        fun visibleLoading(isVisible: Boolean) {
            itemLoadingBinding.root.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }
}