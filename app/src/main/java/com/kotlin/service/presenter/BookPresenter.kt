package com.kotlin.service.presenter

import android.content.Context
import com.kotlin.service.bean.Book
import com.kotlin.service.view.BookView
import com.kotlin.service.view.BaseView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by fzh on 2018/1/19.
 */
class BookPresenter : BasePresenter {
    private lateinit var mBookView: BookView
    private lateinit var mBook: Book

    constructor(context: Context) {
        this.context = context
    }

    override fun attachView(baseView: BaseView?) {
        mBookView = baseView as BookView
    }

    fun getSearchBooks(name: String, tag: String, start: Int, count: Int) {
        compositeSubscription.add(manager.getSearchBooks(name, tag, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Book>() {
                    override fun onError(e: Throwable?) {
                        mBookView.onError(e.toString() + "请求失败！")
                    }

                    override fun onCompleted() {
                        mBookView.onSuccess(mBook)
                    }

                    override fun onNext(t: Book) {
                        mBook = t
                    }

                }))
    }
}