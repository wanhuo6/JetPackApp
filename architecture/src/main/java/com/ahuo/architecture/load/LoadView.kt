package com.ahuo.architecture.load

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import com.ahuo.architecture.R

/**
 * 加载视图
 * @author liuhuijie
 * @date   2021/7/26
 */
class LoadView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    /* private val mBinding = DataBindingUtil.inflate<ViewLoadBinding>(
         LayoutInflater.from(context), R.layout.view_load, this, true
     )*/
    private val gpError: Group
    private val progressBar: ProgressBar
    private val tvEmptyTip: TextView
    private val tvErrorTip: TextView
    private val tvReload: TextView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_load, this, true)
        gpError = view.findViewById(R.id.gpError)
        progressBar = view.findViewById(R.id.progressBar)
        tvEmptyTip = view.findViewById(R.id.tvEmptyTip)
        tvErrorTip=view.findViewById(R.id.tvErrorTip)
        tvReload = view.findViewById(R.id.tvReload)
        setBackgroundColor(Color.WHITE)
        alpha = 1f
    }

    fun showError(block: () -> Unit) {
        gpError.visibility = VISIBLE
        progressBar.visibility = GONE
        tvEmptyTip.visibility = GONE
        tvReload.setOnClickListener {
            block()
        }
    }

    fun showLoading() {
        gpError.visibility = GONE
        progressBar.visibility = VISIBLE
        tvEmptyTip.visibility = GONE
    }

    fun showEmpty() {
        gpError.visibility = GONE
        progressBar.visibility = GONE
        tvEmptyTip.visibility = VISIBLE
    }
}