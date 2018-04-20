package com.kotlin.service.view

import com.kotlin.service.bean.cate.CategoryInfo

/**
 * Created by fzh on 2018/3/29.
 */
interface CateCategoryView : View {

    fun onLoadCateDataSuccess(t: CategoryInfo?)

    fun onLoadCateDataError(s: String?)
}