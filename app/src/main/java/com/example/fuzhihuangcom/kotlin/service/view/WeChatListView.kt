package com.example.fuzhihuangcom.kotlin.service.view

import com.example.fuzhihuangcom.kotlin.service.bean.wechat.WeChatListInfo

/**
 * Created by fzh on 2018/3/29.
 */
interface WeChatListView : View {

    fun onLoadWeChatListSuccess(t: List<WeChatListInfo.ListBean>?)

    fun onLoadWeChatListError(s: String?)
}