package com.ahuo.architecture.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.ahuo.architecture.load.LoadManager

/**
 *
 * @author liuhuijie
 * @date   2021/7/27
 */
interface IContain<DB : ViewDataBinding, VM : BaseViewModel> {

    val variableId: Int
    val mBinding: DB
    val mViewModel: VM
    val mLoadManager: LoadManager

    fun initLoadManager()

    /**
     * 观察数据
     */
    fun observeLiveData(owner: LifecycleOwner) {
        mViewModel.showLoading.observe(owner, {
            if (it) {
                showLoading()
            } else {
                dismissLoading()
            }
        })
        mViewModel.showError.observe(owner, {
            if (it) {
                showError()
            } else {
                dismissError()
            }
        })
        mViewModel.toast.observe(owner, {
            showToast(it)
        })
    }

    /**
     * 初始化viewModel
     */
    fun initViewModel()

    /**
     * 绑定viewModel
     */
    fun bindViewModel() {
        if (variableId != 0) {
            mBinding.setVariable(variableId, mViewModel)
        }
    }

    /**
     * 吐司提示
     */
    fun showToast(message: String) {
       // ToastUtils.showLong(message)
    }

    /**
     * 展示进度
     */
    fun showLoading() {
    }

    /**
     * 关闭进度
     */
    fun dismissLoading() {
    }

    /**
     * 展示错误页面
     */
    fun showError() {
        mLoadManager.showError {
            reLoad()
        }
    }

    /**
     * 隐藏错误页面
     */
    fun dismissError() {
        mLoadManager.showSuccess()
    }

    /**
     * 重新加载数据
     */
    fun reLoad()
}