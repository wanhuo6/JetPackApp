package com.ahuo.jetpackapp.ui

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.ahuo.jetpackapp.BR
import com.ahuo.jetpackapp.R
import com.ahuo.jetpackapp.base.BaseFragment
import com.ahuo.jetpackapp.vm.HomeViewModel

/**
 *
 * @author liuhuijie
 * @date   2021/7/23
 */
class HomeShareFragment :
    BaseFragment<ViewDataBinding, HomeViewModel>(R.layout.fragment_home_share, BR.viewModel,true) {
    override fun initView(savedInstanceState: Bundle?) {
       // mViewModel.startCountDown()
       // mViewModel.getBanner()
    }

    override fun lazyLoadData() {
    }

    override fun reLoad() {
    }
}