package vbn.clean.nation_flag.core.common

import android.view.View
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import vbn.clean.nation_flag.presentation.core.widget.SimpleDividerItemDecoration

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun Any?.toJson(): String = if (this == null) "null" else Gson().toJson(this)

fun ViewPager2.reduceDragSensitivity(f: Int = 3) {
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView
    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, touchSlop * f)       // "8" was obtained experimentally
}

fun RecyclerView.addDivider(@DrawableRes drawable: Int? = null) {
    addItemDecoration(SimpleDividerItemDecoration(context, drawable, this))
}

// Truyền 1 list Position , giữa ở cuối item nào mà không muốn Có line thì thêm vào list
fun RecyclerView.addAddIgnoreDivider(listPosIgnore: List<Int>) {
    val listCount = itemDecorationCount
    if (listCount > 0) {
        for (i in 0..listCount) {
            val item = getItemDecorationAt(i)
            if (item is SimpleDividerItemDecoration) {
                item.addIgnorePosition(listPosIgnore)
                return
            }
        }
    }
}