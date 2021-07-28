package com.ahuo.jetpackapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ahuo.jetpackapp.BR
import com.ahuo.jetpackapp.R
import com.ahuo.jetpackapp.base.BaseActivity
import com.ahuo.jetpackapp.databinding.ActivityHomeBinding
import com.ahuo.jetpackapp.vm.MainViewModel

/**
 *
 * @author liuhuijie
 * @date   2021/7/23
 */
class HomeActivity :
    BaseActivity<ActivityHomeBinding, MainViewModel>(R.layout.activity_home, BR.viewModel) {
    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        mViewModel.startCountDown()
      //  mViewModel.getBanner()
        //mViewModel.getBanner2()
    }

    override fun reLoad() {
        super.reLoad()
       // mViewModel.getBanner()
    }



}