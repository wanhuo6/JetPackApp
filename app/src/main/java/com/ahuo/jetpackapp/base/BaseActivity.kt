package com.ahuo.jetpackapp.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.ahuo.architecture.base.BaseActivity

/**
 *
 * @author liuhuijie
 * @date   2021/7/26
 */
abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel>(
    layoutId: Int = 0,
    variableId: Int = 0
) :
    BaseActivity<DB, VM>(layoutId, variableId) {
}