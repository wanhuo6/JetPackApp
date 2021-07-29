package com.ahuo.architecture.utils

import android.util.Log


/**
 * 日志输出
 *
 * @author liuhuijie
 * @date   2021/7/29
 */
object JetLog {
    private const val TAG = "jetpack_log"
    var logEnable = true
        private set

    fun enableLog(logEnable: Boolean) {
        JetLog.logEnable = logEnable
    }

    fun e(message: String) {
        if (!logEnable) {
            return
        }
        Log.e(TAG, message)
    }

    fun i(message: String) {
        if (!logEnable) {
            return
        }
        Log.i(TAG, message)
    }

    fun d(message: String) {
        if (!logEnable) {
            return
        }
        Log.d(TAG, message)
    }

    fun w(message: String) {
        if (!logEnable) {
            return
        }
        Log.w(TAG, message)
    }
}