package com.kotlin.service.presenter

import android.content.Intent
import com.kotlin.service.view.View

/**
 * Created by fzh on 2018/1/19.
 */
interface Presenter {
    fun onCreate()
    fun onStart()
    fun onStop()
    fun pause()
    fun attachView(view: View)
    fun attachIncomingIntent(intent: Intent)
}