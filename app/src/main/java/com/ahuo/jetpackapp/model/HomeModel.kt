package com.ahuo.jetpackapp.model

import com.ahuo.architecture.net.BaseResponse
import com.ahuo.jetpackapp.entity.BannerEntity
import com.ahuo.jetpackapp.repository.net.apiService
import kotlinx.coroutines.flow.Flow


/**
 *
 * @author liuhuijie
 * @date   2021/7/27
 */
class HomeModel {

    fun getBanner(): Flow<BaseResponse<List<BannerEntity>>> {
        return apiService.getBanner()
    }

    fun getBanner2(string: String): Flow<BaseResponse<List<String>>> {
        return apiService.getBanner2()
    }


}