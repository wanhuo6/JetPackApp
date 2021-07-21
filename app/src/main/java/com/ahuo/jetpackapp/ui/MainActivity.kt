package com.ahuo.jetpackapp.ui

import android.os.Bundle
import com.ahuo.architecture.base.BaseActivity
import com.ahuo.architecture.base.BaseViewModel
import com.ahuo.jetpackapp.R
import com.ahuo.jetpackapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>(R.layout.activity_main) {

    override fun initView(savedInstanceState: Bundle?) {

        mBinding.tvContent.text = "heell"

    }


}