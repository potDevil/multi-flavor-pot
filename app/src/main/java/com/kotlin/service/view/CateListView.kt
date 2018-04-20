package com.kotlin.service.view

import com.kotlin.service.bean.cate.CateDetailListInfo

/**
 * Created by fzh on 2018/4/2.
 */
interface CateListView : View {

    fun onLoadCateListSuccess(t: CateDetailListInfo?)

    fun onLoadCateListError(s: String?)
}