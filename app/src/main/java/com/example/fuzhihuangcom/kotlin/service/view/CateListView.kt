package com.example.fuzhihuangcom.kotlin.service.view

import com.example.fuzhihuangcom.kotlin.service.bean.cate.CateDetailListInfo

/**
 * Created by fzh on 2018/4/2.
 */
interface CateListView : View {

    fun onLoadCateListSuccess(t: CateDetailListInfo?)

    fun onLoadCateListError(s: String?)
}