package com.example.fuzhihuangcom.kotlin.service.view

import com.example.fuzhihuangcom.kotlin.service.bean.wechat.WeChatCategoryInfo

/**
 * Created by fzh on 2018/3/29.
 */
interface WeChatCategoryView : View {

    fun onLoadWeChatDataSuccess(t: List<WeChatCategoryInfo>?)

    fun onLoadWeChatDataError(s: String?)
}