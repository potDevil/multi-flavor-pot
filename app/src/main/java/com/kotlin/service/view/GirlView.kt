package com.kotlin.service.view

import com.kotlin.service.bean.BaiduGirlInfo

/**
 * Created by fzh on 2018/1/19.
 */
interface GirlView : BaseView {
    fun onSuccess(girlInfoList: BaiduGirlInfo)
    fun onError(result: String)
}