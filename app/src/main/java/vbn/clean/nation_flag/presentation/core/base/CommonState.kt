package vbn.clean.nation_flag.presentation.core.base

sealed class CommonState<in T : Any> private constructor() {
    class Success<T : Any>(val data: T) : CommonState<T>()
    object Loading : CommonState<Any>()
    class Fail(val msg: String = "Có lỗi xảy ra") : CommonState<Any>()
    object Init : CommonState<Any>()
}