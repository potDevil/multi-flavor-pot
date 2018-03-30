package com.example.fuzhihuangcom.kotlin.service.view

import com.example.fuzhihuangcom.kotlin.service.bean.cate.CategoryInfo

/**
 * Created by fzh on 2018/3/29.
 */
interface CateCategoryView : View {

    fun onLoadCateDataSuccess(t: CategoryInfo?)

    fun onLoadCateDataError(s: String?)
}