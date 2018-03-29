package com.example.fuzhihuangcom.kotlin.service.view

import com.example.fuzhihuangcom.kotlin.service.bean.weather.LocationInfo

/**
 * Created by fzh on 2018/1/26.
 */
interface LocationView : View {
    fun onSuccess(locationInfos: LocationInfo)
    fun onError(result: String)
}