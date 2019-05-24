package com.kotlin.service.view

import com.kotlin.service.bean.wechat.WeChatListInfo

/**
 * Created by fzh on 2018/3/29.
 */
interface WeChatListView : BaseView {

    fun onLoadWeChatListSuccess(t: List<WeChatListInfo.ListBean>?)

    fun onLoadWeChatListError(s: String?)
}