package com.kotlin.service.view

import com.kotlin.service.bean.Book

/**
 * Created by fzh on 2018/1/23.
 */
interface BookView : BaseView {
    fun onSuccess(book: Book)
    fun onError(result: String)
}