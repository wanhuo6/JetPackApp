package com.ahuo.jetpackapp.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.ahuo.architecture.base.BaseFragment

/**
 *
 * @author liuhuijie
 * @date   2021/7/26
 */
abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel>(
    layoutId: Int = 0,
    variableId: Int = 0,
    useActivityViewModelStoreOwner: Boolean = false
) : BaseFragment<DB, VM>(layoutId, variableId, useActivityViewModelStoreOwner) {

    abstract override fun initView(savedInstanceState: Bundle?)

    override fun lazyLoadData() {
    }
}