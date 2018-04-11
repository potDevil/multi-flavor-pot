package com.example.fuzhihuangcom.kotlin

import android.app.Application
import com.tencent.smtt.sdk.QbSdk

/**
 * Created by fzh on 2018/1/23.
 */

@SuppressWarnings("all")
class PotApp : Application() {
    companion object {
        private var instance: Application? = null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //初始化 x5内核
        Thread(Runnable {
            val cb = object : QbSdk.PreInitCallback {
                override fun onCoreInitFinished() {

                }

                override fun onViewInitFinished(b: Boolean) {

                }
            }
            QbSdk.initX5Environment(applicationContext, cb)
        }).start()
    }
}