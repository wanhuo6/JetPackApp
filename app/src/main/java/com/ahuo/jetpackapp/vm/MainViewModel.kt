package com.ahuo.jetpackapp.vm

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahuo.architecture.ext.logE
import com.ahuo.architecture.net.ErrorDealType
import com.ahuo.jetpackapp.base.BaseViewModel
import com.ahuo.jetpackapp.model.HomeModel
import com.ahuo.jetpackapp.ui.HomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch

/**
 * @author liuhuijie
 * @date 2021/7/21
 */
class MainViewModel : BaseViewModel() {

    private val homeModel: HomeModel by lazy { HomeModel() }

    val count: MutableLiveData<Int> by lazy { MutableLiveData(60) }

    val imageUrl: MutableLiveData<String> by lazy { MutableLiveData("https://tse2-mm.cn.bing.net/th/id/OIP-C.MWduK7tkhTGGOgFM2QLNMAHaEo?pid=ImgDet&rs=1") }


    fun startCountDown() {
        viewModelScope.launch {
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

    fun getBanner2() {
        request {
            homeModel.getBanner()
                .flatMapConcat {
                    homeModel.getBanner2(it.message ?: "")
                }.uiLoading()
                .catchError(ErrorDealType.TYPE_INIT)
                .collect { res ->
                    res.data?.let {
                        "===".logE()
                    } ?: "data为空".logE()
                }
        }
    }

    fun getBanner() {
        request(
            { homeModel.getBanner() }, {
                ("---" + it?.get(0)?.desc).logE()
            }, {
                ("---===" + it.message).logE()
            }, ErrorDealType.TYPE_TOAST
        )

    }

    fun countClick(view: View) {
        HomeActivity.startActivity(view.context)
    }


}