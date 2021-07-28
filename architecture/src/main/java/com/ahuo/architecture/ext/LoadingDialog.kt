package com.ahuo.architecture.ext

import android.app.Activity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ahuo.architecture.R

/**
 * 加载弹窗扩展
 * @author liuhuijie
 * @date   2021/7/25
 */

//loading框
private var loadingDialog: AlertDialog? = null

/**
 * 打开等待框
 */
fun AppCompatActivity.showLoadingExt(message: String = "请求网络中") {
    if (!this.isFinishing) {
        if (loadingDialog == null) {
            loadingDialog =
                AlertDialog.Builder(this).setView(R.layout.layout_custom_progress_dialog).create()
        }
        loadingDialog?.show()
    }
}

/**
 * 打开等待框
 */
fun Fragment.showLoadingExt(message: String = "请求网络中") {
    activity?.let {
        if (!it.isFinishing) {
            if (loadingDialog == null) {
                loadingDialog =
                    AlertDialog.Builder(context).setView(R.layout.layout_custom_progress_dialog)
                        .create()
            }
            loadingDialog?.show()
        }
    }
}

/**
 * 关闭等待框
 */
fun Activity.dismissLoadingExt() {
    loadingDialog?.dismiss()
    loadingDialog = null
}

/**
 * 关闭等待框
 */
fun Fragment.dismissLoadingExt() {
    loadingDialog?.dismiss()
    loadingDialog = null
}

