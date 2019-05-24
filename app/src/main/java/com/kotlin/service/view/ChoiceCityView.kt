package com.kotlin.service.view

import com.kotlin.service.bean.weather.ChinaCityInfo

/**
 * Created by fzh on 2018/1/30.
 */
interface ChoiceCityView : BaseView {

    fun onProvinceSuccess(cityInfos: List<ChinaCityInfo>?)

    fun onProvinceError(error: String?)

    fun onCitySuccess(cityInfos: List<ChinaCityInfo>?)

    fun onCityError(error: String?)

    fun onCountySuccess(cityInfos: List<ChinaCityInfo>?)

    fun onCountyError(error: String?)
}