package com.example.fuzhihuangcom.kotlin.service.view

import com.example.fuzhihuangcom.kotlin.service.bean.weather.ChinaCityInfo

/**
 * Created by fzh on 2018/1/30.
 */
interface ChoiceCityView : View {

    fun onProvinceSuccess(cityInfos: List<ChinaCityInfo>?)

    fun onProvinceError(error: String?)

    fun onCitySuccess(cityInfos: List<ChinaCityInfo>?)

    fun onCityError(error: String?)

    fun onCountySuccess(cityInfos: List<ChinaCityInfo>?)

    fun onCountyError(error: String?)
}