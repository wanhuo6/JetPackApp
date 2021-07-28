package com.ahuo.architecture.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahuo.architecture.net.BaseResponse
import com.ahuo.architecture.net.ErrorDealType
import com.ahuo.architecture.net.ResponseError
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * 基类ViewModel
 * @author liuhuijie
 * @date   2021/7/20
 */
open class BaseViewModel : ViewModel() {

    /**
     * 数据加载
     */
    val showLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    /**
     * 展示错误页面
     */
    val showError: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    /**
     * Toast
     */
    val toast: MutableLiveData<String> by lazy { MutableLiveData<String>() }


    /**
     * 普通网络请求
     */
    fun request(block: suspend () -> Unit) {
        viewModelScope.launch {
            block()
        }
    }

    /**
     * 过滤服务器结果，失败抛异常
     * @param block 请求体方法，必须要用suspend关键字修饰
     * @param onSuccess 成功回调
     * @param onError 失败回调 可不传
     * @param errorDealType 失败处理方式
     * @param isShowLoading 是否显示加载框
     */
    fun <T> request(
        block: () -> Flow<BaseResponse<T>>,
        onSuccess: (T?) -> Unit,
        onError: (ResponseError) -> Unit = {},
        errorDealType: Int = ErrorDealType.TYPE_TOAST,
        isShowLoading: Boolean = true,
    ): Job {
        return viewModelScope.launch {
            block().onStart {
                if (isShowLoading) showLoading.postValue(isShowLoading)
            }.onCompletion {
                showLoading.postValue(false)
            }.catch {
                val responseError = ResponseError.getEntity(it)
                dealError(errorDealType, responseError)
                onError(responseError)
            }.collect {
                if (it.success) {
                    showError.postValue(false)
                    onSuccess(it.data)
                } else {
                    val responseError = ResponseError(it.code, it.message)
                    dealError(errorDealType, responseError)
                    onError(ResponseError(it.code, it.message))
                }
            }
        }
    }

    /**
     * 处理错误信息
     */
    private fun dealError(errorDealType: Int, error: ResponseError) {
        if (errorDealType == ErrorDealType.TYPE_TOAST) {
            // ToastUtils.showLong(error.message)
            toast.postValue(error.message)
        } else if (errorDealType == ErrorDealType.TYPE_INIT) {
            showError.postValue(true)
        }
    }

    /**
     * 为请求添加loading
     */
    fun <T : BaseResponse<*>> Flow<T>.uiLoading(): Flow<T> = onStart { showLoading.value = true }
        .onCompletion { showLoading.value = false }

    /**
     * 请求时网络异常
     */
    fun <T : BaseResponse<*>> Flow<T>.catchError(errorDealType: Int= ErrorDealType.TYPE_TOAST): Flow<T> = catch {
        it.printStackTrace()
        dealError(errorDealType, ResponseError.getEntity(it))
    }


}