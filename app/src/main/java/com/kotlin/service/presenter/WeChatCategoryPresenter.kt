package com.kotlin.service.presenter

import android.content.Context
import com.kotlin.service.bean.HttpListResp
import com.kotlin.service.bean.wechat.WeChatCategoryInfo
import com.kotlin.service.view.BaseView
import com.kotlin.service.view.WeChatCategoryView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by fzh on 2018/4/9.
 */
class WeChatCategoryPresenter : BasePresenter {
    private lateinit var weChatCategoryView: WeChatCategoryView

    constructor(context: Context) {
        this.context = context
    }

    override fun attachView(baseView: BaseView?) {
        super.attachView(baseView)
        weChatCategoryView = baseView as WeChatCategoryView
    }

    fun getWeChatCategory() {
        compositeSubscription.add(manager.getWeChatCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<HttpListResp<WeChatCategoryInfo>>() {

                    override fun onCompleted() {

                    }

                    override fun onNext(t: HttpListResp<WeChatCategoryInfo>?) {
                        weChatCategoryView.onLoadWeChatDataSuccess(t?.result)
                    }

                    override fun onError(e: Throwable?) {
                        weChatCategoryView.onLoadWeChatDataError(e.toString())
                    }
                }))
    }
}