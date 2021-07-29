package com.ahuo.jetpackapp.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ahuo.architecture.ext.logE
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.lang.Exception

/**
 * 图片加载
 * @author liuhuijie
 * @date   2021/7/29
 */

/**
 * 加载普通图片
 * @param url 路径
 * @param defaultId 默认图
 * @param isCircle 是否圆形
 */
@BindingAdapter(value = ["imageUrl", "defaultId", "isCircle"], requireAll = false)
fun ImageView.loadNormal(
    url: String?,
    defaultId: Int = -1,
    isCircle: Boolean = false
) {
    try {
        var options = RequestOptions()
        if (defaultId != -1) {
            options = options.placeholder(defaultId) //设置加载未完成时的占位图
                .error(defaultId) //设置加载异常时的占位图
        }
        if (isCircle) {
            options = options.circleCrop()
        }
        Glide.with(this)
            .load(url)
            .apply(options)
            .into(this)
    } catch (e: Exception) {
        ("加载图片失败" + e.message).logE()
    }
}