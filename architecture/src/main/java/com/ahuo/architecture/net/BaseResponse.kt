package com.ahuo.architecture.net

data class BaseResponse<T>(
    var success: Boolean = false,
    var data: T? = null,
    var code: Int? = 0,
    var message: String? = "未知错误"
)