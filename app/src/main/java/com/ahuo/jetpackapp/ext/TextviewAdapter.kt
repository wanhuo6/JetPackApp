package com.ahuo.jetpackapp.ext

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 *
 * @author liuhuijie
 * @date   2021/7/29
 */
@SuppressLint("SetTextI18n")
@BindingAdapter("setCuntDownSecond")
fun TextView.setCuntDownSecond(second: Int) {
    text = "$second"+"s"
}