package com.example.fuzhihuangcom.kotlin.service.presenter

import android.content.Context
import com.example.fuzhihuangcom.kotlin.service.bean.HttpResp
import com.example.fuzhihuangcom.kotlin.service.bean.cate.CateDetailListInfo
import com.example.fuzhihuangcom.kotlin.service.view.CateListView
import com.example.fuzhihuangcom.kotlin.service.view.View
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by fzh on 2018/4/2.
 */
class CateListPresenter : BasePresenter {

    private lateinit var cateListView: CateListView

    constructor(context: Context) {
        mContext = context
    }

    override fun attachView(view: View) {
        super.attachView(view)
        cateListView = view as CateListView
    }

    fun getCateListInfo(cid: String, page: Int, size: Int) {
        mCompositeSubscription.add(mManager.getCateData(cid, page, size)
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