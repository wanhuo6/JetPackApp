package com.ahuo.architecture.ext

import com.ahuo.architecture.utils.JetLog

/**
 * 字符串扩展
 * @author liuhuijie
 * @date   2021/7/29
 */
fun String.logE() {
    JetLog.e(this)
}

fun String.logD() {
    JetLog.d(this)
}

fun String.logI() {
    JetLog.i(this)
}

fun String.logW() {
    JetLog.w(this)
}

