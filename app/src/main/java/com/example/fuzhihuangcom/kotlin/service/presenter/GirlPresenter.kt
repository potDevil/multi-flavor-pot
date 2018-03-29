package com.example.fuzhihuangcom.kotlin.service.presenter

import android.content.Context
import com.example.fuzhihuangcom.kotlin.service.bean.BaiduGirlInfo
import com.example.fuzhihuangcom.kotlin.service.view.GirlView
import com.example.fuzhihuangcom.kotlin.service.view.View
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
        mContext = context
    }

    override fun attachView(view: View) {
        mGirlView = view as GirlView
    }

    fun getGirlInfo(num: Int) {
        mCompositeSubscription.add(mManager.getGirlInfo(num)
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