package vbn.clean.nation_flag.presentation.my_interface

interface OnClickItemFlag {
    fun callback(tag: TagFlag, data: Any?)

    enum class TagFlag {
        ON_CLICK_ITEM_FLAG
    }
}