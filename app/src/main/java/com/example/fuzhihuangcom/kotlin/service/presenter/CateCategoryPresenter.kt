package com.example.fuzhihuangcom.kotlin.service.presenter

import android.content.Context
import com.example.fuzhihuangcom.kotlin.service.bean.cate.CategoryInfo
import com.example.fuzhihuangcom.kotlin.service.view.CateCategoryView
import com.example.fuzhihuangcom.kotlin.service.view.View
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by fzh on 2018/3/29.
 */
class CateCategoryPresenter : BasePresenter {

    private lateinit var cateCategoryView: CateCategoryView

    constructor(context: Context) {
        mContext = context
    }

    override fun attachView(view: View) {
        super.attachView(view)
        cateCategoryView = view as CateCategoryView
    }

    fun getCateCategory() {
        mCompositeSubscription.add(mManager.getCateCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<CategoryInfo>() {
                    override fun onCompleted() {

                    }

                    override fun onNext(t: CategoryInfo?) {
                        cateCategoryView.onLoadCateDataSuccess(t)
                    }

                    override fun onError(e: Throwable?) {
                        cateCategoryView.onLoadCateDataError(e.toString())
                    }
                }))
    }
}