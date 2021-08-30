package com.ahuo.jetpackapp.ui

import android.os.Bundle
import com.ahuo.jetpackapp.BR
import com.ahuo.jetpackapp.R
import com.ahuo.jetpackapp.base.BaseActivity
import com.ahuo.jetpackapp.databinding.ActivityMainBinding
import com.ahuo.jetpackapp.repository.db.AppDatabase
import com.ahuo.jetpackapp.vm.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main, BR.viewModel) {

    private lateinit var appDatabase: AppDatabase
    override fun initView(savedInstanceState: Bundle?) {

        //.addMigrations(AppDatabase.MIGRATION_1_2).addMigrations(AppDatabase.MIGRATION_2_3)

        GlobalScope.launch {
            delay(1000)
          //  appDatabase.userDao().insertAll(UserEntity(1, "李梅", 11, 21))
        }

    }

    override fun reLoad() {
        mViewModel.getBanner()
    }


}