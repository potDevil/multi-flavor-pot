package com.kotlin.service.presenter

import android.content.Context
import com.kotlin.service.bean.HttpResp
import com.kotlin.service.bean.cate.CateDetailListInfo
import com.kotlin.service.view.CateListView
import com.kotlin.service.view.BaseView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by fzh on 2018/4/2.
 */
class CateListPresenter : BasePresenter {

    private lateinit var cateListView: CateListView

    constructor(context: Context) {
        this.context = context
    }

    override fun attachView(baseView: BaseView?) {
        super.attachView(baseView)
        cateListView = baseView as CateListView
    }

    fun getCateListInfo(cid: String, page: Int, size: Int) {
        compositeSubscription.add(manager.getCateData(cid, page, size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<HttpResp<CateDetailListInfo>>() {
                    override fun onCompleted() {

                    }

                    override fun onNext(t: HttpResp<CateDetailListInfo>?) {
                        cateListView.onLoadCateListSuccess(t?.result)
                    }

                    override fun onError(e: Throwable?) {
                        cateListView.onLoadCateListError(e?.toString())
                    }
                }))
    }
}