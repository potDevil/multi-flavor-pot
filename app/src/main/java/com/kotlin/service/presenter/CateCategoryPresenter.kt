package com.kotlin.service.presenter

import android.content.Context
import com.kotlin.service.bean.HttpResp
import com.kotlin.service.bean.cate.CategoryInfo
import com.kotlin.service.view.CateCategoryView
import com.kotlin.service.view.View
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by fzh on 2018/3/29.
 */
class CateCategoryPresenter : BasePresenter {

    private lateinit var cateCategoryView: CateCategoryView

    constructor(context: Context) {
        this.context = context
    }

    override fun attachView(view: View) {
        super.attachView(view)
        cateCategoryView = view as CateCategoryView
    }

    fun getCateCategory() {
        compositeSubscription.add(manager.getCateCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<HttpResp<CategoryInfo>>() {
                    override fun onCompleted() {

                    }

                    override fun onNext(t: HttpResp<CategoryInfo>?) {
                        cateCategoryView.onLoadCateDataSuccess(t?.result)
                    }

                    override fun onError(e: Throwable?) {
                        cateCategoryView.onLoadCateDataError(e.toString())
                    }
                }))
    }
}