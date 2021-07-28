package com.ahuo.architecture.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @author liuhuijie
 * @date   2021/7/28
 */
class NetManager {

    private var baseUrl: String? = null
    private var retrofit: Retrofit? = null

    companion object {
        private const val CONNECT_TIME_OUT = 10
        private const val READ_TIME_OUT = 30
        private const val WRITE_TIME_OUT = 30
        val instance: NetManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetManager()
        }
    }

    /**
     * 初始化BaseUrl
     *
     * @param baseUrl
     */
    fun setBaseUrl(baseUrl: String?) {
        this.baseUrl = baseUrl
    }

    /**
     * 获取全局retrofit
     */
    fun getRetrofitInstance(
        url: String = baseUrl ?: "",
        retrofitInit: (Retrofit.Builder) -> Unit = {},
        okInit: (OkHttpClient.Builder) -> Unit = {}
    ): Retrofit {
        if (retrofit == null) {
            retrofit = getRetrofit(url, retrofitInit, okInit)
        }
        return retrofit!!
    }

    /**
     * 获取retrofit
     */
    fun getRetrofit(
        url: String = baseUrl ?: "",
        retrofitInit: (Retrofit.Builder) -> Unit = {},
        okInit: (OkHttpClient.Builder) -> Unit = {}
    ): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(
            OkHttpClient.Builder().apply {
                connectTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.SECONDS)
                writeTimeout(WRITE_TIME_OUT.toLong(), TimeUnit.SECONDS)
                readTimeout(READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
                retryOnConnectionFailure(true)
                okInit.invoke(this)
            }.build()
        )
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .apply { retrofitInit.invoke(this) }
        .build()
}