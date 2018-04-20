package com.kotlin.service.view

import com.kotlin.service.bean.weather.LocationInfo

/**
 * Created by fzh on 2018/1/26.
 */
interface LocationView : View {
    fun onSuccess(locationInfos: LocationInfo)
    fun onError(result: String)
}