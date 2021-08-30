package com.ahuo.jetpackapp

import android.app.Application
import com.facebook.stetho.Stetho

/**
 *
 * @author liuhuijie
 * @date   2021/7/21
 */
class JetpackApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }

    }
}