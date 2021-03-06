package com.kotlin.service.presenter

import android.content.Context
import com.kotlin.service.bean.HttpResp
import com.kotlin.service.bean.wechat.WeChatListInfo
import com.kotlin.service.view.BaseView
import com.kotlin.service.view.WeChatListView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by fzh on 2018/4/10.
 */
class WeChatListPresenter : BasePresenter {

    private lateinit var weChatListView: WeChatListView

    constructor(context: Context) {
        this.context = context
    }

    override fun attachView(baseView: BaseView?) {
        super.attachView(baseView)
        weChatListView = baseView as WeChatListView
    }

    fun getWeChatList(cid: String, page: Int, size: Int) {
        compositeSubscription.add(manager.getWeChatList(cid, page, size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<HttpResp<WeChatListInfo>>() {

                    override fun onCompleted() {

                    }

                    override fun onNext(t: HttpResp<WeChatListInfo>?) {
                        weChatListView.onLoadWeChatListSuccess(t?.result?.list)
                    }

                    override fun onError(e: Throwable?) {
                        weChatListView.onLoadWeChatListError(e.toString())
                    }

                })
        )
    }
}