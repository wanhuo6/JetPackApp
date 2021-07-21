package com.ahuo.jetpackapp.ui

import android.os.Bundle
import com.ahuo.architecture.base.BaseActivity
import com.ahuo.jetpackapp.BR
import com.ahuo.jetpackapp.R
import com.ahuo.jetpackapp.databinding.ActivityMainBinding
import com.ahuo.jetpackapp.vm.MainViewModel

class MainActivity :
    BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main,BR.viewModel) {

    override fun initView(savedInstanceState: Bundle?) {
        mViewModel.startCountDown()
    }


}