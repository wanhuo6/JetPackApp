package com.ahuo.jetpackapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.ahuo.jetpackapp.base.BaseActivity
import com.ahuo.jetpackapp.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * @author liuhuijie
 * @date   2021/8/9
 */
class SplashActivity : BaseActivity<ViewDataBinding, BaseViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            delay(3000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }


}