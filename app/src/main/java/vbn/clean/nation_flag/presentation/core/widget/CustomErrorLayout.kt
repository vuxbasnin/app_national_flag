package vbn.clean.nation_flag.presentation.core.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import vbn.clean.nation_flag.databinding.CustomErrorLayoutBinding

class CustomErrorLayout(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {
    var onRetryClickListener: (() -> Unit)? = null
    var binding: CustomErrorLayoutBinding

    init {
        binding = CustomErrorLayoutBinding.inflate(LayoutInflater.from(context), this, true)
        binding.btnRetry.setOnClickListener {
            onRetryClickListener?.invoke()
        }
    }
}