package com.ahuo.architecture.net

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author liuhuijie
 * @date   2021/7/22
 */
class FlowCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Flow::class.java) return null
        check(returnType is ParameterizedType) { "Flow return type must be parameterized as Flow<Foo> or Flow<out Foo>" }
        val responseType = getParameterUpperBound(0, returnType)
        val rawFlowType = getRawType(responseType)
        return if (rawFlowType == Response::class.java) {
            check(responseType is ParameterizedType) { "Response must be parameterized as Response<Foo> or Response<out Foo>" }
            ResponseCallAdapter<Any>(getParameterUpperBound(0, responseType))
        } else {
            BodyCallAdapter<Any>(responseType)
        }
    }

    class ResponseCallAdapter<T>(private val responseType: Type) :
        CallAdapter<T, Flow<Response<T>>> {
        override fun responseType() = responseType
        override fun adapt(call: Call<T>): Flow<Response<T>> {
            return flow { emit(call.awaitResponse()) }
        }
    }

    class BodyCallAdapter<T : Any>(private val responseType: Type) : CallAdapter<T, Flow<T>> {
        override fun responseType() = responseType
        override fun adapt(call: Call<T>): Flow<T> {
            return flow { emit(call.await()) }
        }
    }

    companion object {
        @JvmStatic
        fun create() = FlowCallAdapterFactory()
    }
}