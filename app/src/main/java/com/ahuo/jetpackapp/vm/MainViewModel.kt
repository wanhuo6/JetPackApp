package com.ahuo.jetpackapp.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahuo.architecture.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author liuhuijie
 * @date 2021/7/21
 */
class MainViewModel : BaseViewModel() {

    var count = MutableLiveData<Int>()

    init {
        count.value = 60
    }

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


}