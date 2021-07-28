package com.ahuo.jetpackapp.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahuo.architecture.net.ErrorDealType
import com.ahuo.jetpackapp.base.BaseViewModel
import com.ahuo.jetpackapp.model.HomeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author liuhuijie
 * @date 2021/7/23
 */
class HomeViewModel : BaseViewModel() {

    private val homeModel: HomeModel by lazy { HomeModel() }

    val count: MutableLiveData<Int> by lazy { MutableLiveData(60) }


    fun startCountDown() {
        viewModelScope.launch(Dispatchers.Default) {
            while (true) {
                delay(1000)
                val value = count.value!!.minus(1)
                if (value < 0) {
                    break
                }
                count.postValue(value)
            }
        }
    }

    fun getBanner() {
        request(
            { homeModel.getBanner() }, {
                Log.e("jetpackApp", "home---" + it?.get(0)?.desc)
            }, {
                Log.e("jetpackApp", "home---===" + it.message)
            }, ErrorDealType.TYPE_INIT
        )

    }
}