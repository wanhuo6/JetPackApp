package com.ahuo.architecture.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.ahuo.architecture.ext.getVmClazz

/**
 * 基类Activity
 * @author liuhuijie
 * @date 2021/7/20
 */
abstract class BaseActivity<VM : BaseViewModel,DB : ViewDataBinding>(
    private val layoutId: Int,
    private val variableId: Int = 0
) :
    AppCompatActivity() {
    lateinit var mBinding: DB
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initViewModel()
        bindViewModel()
        initView(savedInstanceState)

    }

    private fun bindViewModel() {
        if (variableId != 0) {
            mBinding.setVariable(variableId, mViewModel)
        }
    }

    private fun initDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, layoutId)
        mBinding.lifecycleOwner = this
    }

    private fun initViewModel() {
        mViewModel = createViewModel()
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)


}