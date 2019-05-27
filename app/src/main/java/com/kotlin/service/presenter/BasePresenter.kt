package com.kotlin.service.presenter

import android.content.Context
import android.content.Intent
import com.kotlin.service.manager.DataManager
import com.kotlin.service.view.BaseView
import rx.subscriptions.CompositeSubscription

/**
 * Created by fzh on 2018/1/22.
 */
abstract class BasePresenter : Presenter {
    protected lateinit var context: Context
    protected lateinit var manager: DataManager
    protected lateinit var compositeSubscription: CompositeSubscription

    override fun onCreate() {
        manager = DataManager()
        compositeSubscription = CompositeSubscription()
    }

    override fun onStart() {
    }

    override fun onStop() {
        if (compositeSubscription.hasSubscriptions()) {
            // compositeSubscription.unsubscribe()解除绑定并且之后的绑定都无效
            // compositeSubscription.clear()      解除绑定但是之后仍然可以继续绑定
            compositeSubscription.unsubscribe()

        }
    }

    override fun pause() {
    }

    override fun attachView(baseView: BaseView?) {
    }

    override fun attachIncomingIntent(intent: Intent) {
    }
}