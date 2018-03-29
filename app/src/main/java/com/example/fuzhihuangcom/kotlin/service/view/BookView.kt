package com.example.fuzhihuangcom.kotlin.service.view

import com.example.fuzhihuangcom.kotlin.service.bean.Book

/**
 * Created by fzh on 2018/1/23.
 */
interface BookView : View {
    fun onSuccess(book: Book)
    fun onError(result: String)
}