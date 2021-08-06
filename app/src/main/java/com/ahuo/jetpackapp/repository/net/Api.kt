package com.ahuo.jetpackapp.repository.net

import com.ahuo.architecture.net.BaseResponse
import com.ahuo.jetpackapp.entity.BannerEntity
import com.ahuo.jetpackapp.entity.HotKeyEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

/**
 * 网络接口
 * @author liuhuijie
 * @date   2021/7/21
 */
interface Api {
    @GET("banner/json")
    fun getBanner(): Flow<BaseResponse<List<BannerEntity>>>

    @GET("banner/json2")
    fun getBanner2(): Flow<BaseResponse<List<String>>>

    @GET("hotkey/json")
    fun getHotKey(): Flow<BaseResponse<List<HotKeyEntity>>>
}