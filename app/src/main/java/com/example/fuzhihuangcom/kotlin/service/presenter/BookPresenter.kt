package com.example.fuzhihuangcom.kotlin.service.presenter

import android.content.Context
import com.example.fuzhihuangcom.kotlin.service.bean.Book
import com.example.fuzhihuangcom.kotlin.service.view.BookView
import com.example.fuzhihuangcom.kotlin.service.view.View
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

    override fun attachView(view: View) {
        mBookView = view as BookView
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