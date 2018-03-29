package com.example.fuzhihuangcom.kotlin

import android.app.Application

/**
 * Created by fzh on 2018/1/23.
 */

@SuppressWarnings("all")
class PotApp : Application() {
    companion object {
        private var instance: Application? = null
        fun instance () = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}