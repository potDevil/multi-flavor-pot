package com.kotlin.service.presenter

import android.content.Context
import com.kotlin.service.bean.BaiduGirlInfo
import com.kotlin.service.view.GirlView
import com.kotlin.service.view.BaseView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by fzh on 2018/1/23.
 */
class GirlPresenter : BasePresenter {
    private lateinit var mGirlView: GirlView
//    private lateinit var mGirlInfo: GirlInfo

    constructor(context: Context) {
        this.context = context
    }

    override fun attachView(baseView: BaseView?) {
        mGirlView = baseView as GirlView
    }

    fun getGirlInfo(num: Int) {
        compositeSubscription.add(manager.getGirlInfo(num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<BaiduGirlInfo>() {
                    override fun onNext(t: BaiduGirlInfo) {
                        mGirlView.onSuccess(t)
                    }

                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {
                        mGirlView.onError(e.toString() + "请求失败")
                    }

                }))

    }
}