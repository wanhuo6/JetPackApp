package com.ahuo.jetpackapp.ui

import android.os.Bundle
import com.ahuo.jetpackapp.BR
import com.ahuo.jetpackapp.R
import com.ahuo.jetpackapp.base.BaseFragment
import com.ahuo.jetpackapp.databinding.FragmentHomeBinding
import com.ahuo.jetpackapp.vm.HomeViewModel

/**
 *
 * @author liuhuijie
 * @date   2021/7/23
 */
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home, BR.viewModel,true) {
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun lazyLoadData() {
        mViewModel.startCountDown()
        mViewModel.getBanner()
        mBinding.tvJump.setOnClickListener {
/*
            HomeFragmentArgs("li", 20).toBundle().run {
                Navigation.findNavController(it)
                    .navigate(R.id.action_homeFragment_to_homeShareFragment, this)
            }
*/

        }
    }

    override fun reLoad() {
        mViewModel.getBanner()
    }
}