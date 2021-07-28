package com.ahuo.architecture.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.ahuo.architecture.ext.dismissLoadingExt
import com.ahuo.architecture.ext.getVmClazz
import com.ahuo.architecture.ext.showLoadingExt
import com.ahuo.architecture.load.LoadManager

/**
 * 基类Activity
 * @author liuhuijie
 * @date 2021/7/20
 */
abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel>(
    private val layoutId: Int,
    override val variableId: Int = 0
) : AppCompatActivity(), IContain<ViewDataBinding, BaseViewModel> {

    override lateinit var mLoadManager: LoadManager
    override lateinit var mBinding: DB
    override lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLoadManager()
        initDataBinding()
        initViewModel()
        observeLiveData(this)
        bindViewModel()
        initView(savedInstanceState)

    }


    /**
     * 初始化loadManager
     */
    override fun initLoadManager() {
        mLoadManager = LoadManager(this)
    }

    /**
     * 绑定viewModel
     */
    override fun bindViewModel() {
        if (variableId != 0) {
            mBinding.setVariable(variableId, mViewModel)
        }
    }

    /**
     * 初始化dataBinding
     */
    private fun initDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, layoutId)
        mBinding.lifecycleOwner = this
    }

    /**
     * 创建viewModel
     */
    override fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 展示进度
     */
    override fun showLoading() {
        showLoadingExt()
    }


    /**
     * 关闭进度
     */
    override fun dismissLoading() {
        dismissLoadingExt()
    }

    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)


}