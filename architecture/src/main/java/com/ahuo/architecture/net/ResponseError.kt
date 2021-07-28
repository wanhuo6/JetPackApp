package com.ahuo.architecture.net

import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.ParseException
import java.util.concurrent.CancellationException


/**
 * 请求返回错误（网络错误 后台错误）
 * @author liuhuijie
 * @date 2021/7/22
 */
class ResponseError(
    var code: Int? = -1,
    var message: String? = "未知错误",
) {
    companion object {
        const val UNKNOWN = -100
        const val CONNECT_ERROR = -101
        const val CONNECT_TIMEOUT = -102
        const val BAD_NETWORK = -103
        const val PARSE_ERROR = -104
        const val CANCEL_REQUEST = -105

        /**
         * 解析网络请求异常
         */
        fun getEntity(e: Throwable): ResponseError {
            return when (e) {
                is HttpException -> ResponseError(
                    BAD_NETWORK,
                    "网络异常，请稍后重试"
                )
                is ConnectException, is UnknownHostException -> ResponseError(
                    CONNECT_ERROR,
                    "链接错误，请稍后重试"
                )
                is InterruptedIOException -> ResponseError(
                    CONNECT_TIMEOUT,
                    "连接超时，请稍后重试"
                )
                is JsonParseException, is JSONException, is ParseException, is ClassCastException -> ResponseError(
                    PARSE_ERROR,
                    "解析异常"
                )
                is CancellationException -> ResponseError(
                    CANCEL_REQUEST,
                    "网络请求已取消"
                )
                else -> ResponseError(UNKNOWN, "网络异常，请稍后重试")
            }
        }
    }
}