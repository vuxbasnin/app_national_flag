package vbn.clean.nation_flag.core.common

import android.view.View
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import vbn.clean.nation_flag.presentation.core.widget.SimpleDividerItemDecoration
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

fun String?.getDateOrTime(patternInput: TagDatePattern, patternOutput: TagDatePattern): String {
    var result = ""
    runCatching {
        // Define the input date format
        val inputFormat = SimpleDateFormat(patternInput.value, Locale.ENGLISH)

        // Parse the date string into a Date object
        val date: Date? = inputFormat.parse(this)

        // Define the desired output time format
        val outputFormat = SimpleDateFormat(patternOutput.value, Locale.ENGLISH)

        // Format the Date object into the desired time format
        result = outputFormat.format(date)
    }.onFailure { exception: Throwable ->
        exception.printStackTrace()
        result = "--/--/----"
    }
    return result
}

enum class TagDatePattern(val value: String) {  // 6/5/2024 3:00:00 PM
    TIME_SHOW_DAY_MONTH_YEAR("dd/MM/yyyy"),
    TIME_SERVER("yyyy-MM-dd'T'HH:mm:ss"),
    TIME_SERVER3("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
    TIME_SERVER_2("dd/MM/yyyy hh:mm:ss"),
    TIME_SERVER_4("MM/dd/yyyy hh:mm:ss"),
    TIME_SERVER_5("yyyy-MM-dd'T'HH:mm:ss'Z'"),
    TIME_SELECT_CALENDAR("EEE MMM dd HH:mm:ss zzz yyyy"),
    TIME_HOUR_MIN_SEC("HH:mm:ss"),
    TIME_HOUR_MIN("HH:mm"),
}