package com.example.fuzhihuangcom.kotlin.service.view

import com.example.fuzhihuangcom.kotlin.service.bean.BaiduGirlInfo

/**
 * Created by fzh on 2018/1/19.
 */
interface GirlView : View {
    fun onSuccess(girlInfoList: BaiduGirlInfo)
    fun onError(result: String)
}