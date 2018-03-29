package com.example.fuzhihuangcom.kotlin.service.presenter

import android.content.Context
import android.content.Intent
import com.example.fuzhihuangcom.kotlin.service.manager.DataManager
import com.example.fuzhihuangcom.kotlin.service.view.View
import rx.subscriptions.CompositeSubscription

/**
 * Created by fzh on 2018/1/22.
 */
abstract class BasePresenter : Presenter {
    protected lateinit var mContext: Context
    protected lateinit var mManager: DataManager
    protected lateinit var mCompositeSubscription: CompositeSubscription

    override fun onCreate() {
        mManager = DataManager()
        mCompositeSubscription = CompositeSubscription()
    }

    override fun onStart() {
    }

    override fun onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe()
        }
    }

    override fun pause() {
    }

    override fun attachView(view: View) {
    }

    override fun attachIncomingIntent(intent: Intent) {
    }
}