package com.ahuo.jetpackapp.repository.net

import com.ahuo.architecture.net.NetManager
import retrofit2.create

/**
 *
 * @author liuhuijie
 * @date   2021/7/22
 */
val apiService: Api by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetManager.instance.getRetrofitInstance("https://wanandroid.com/").create()
}