package com.kotlin.service.view

import com.kotlin.service.bean.weather.WeatherInfo
import okhttp3.ResponseBody

/**
 * Created by fzh on 2018/1/31.
 */
interface WeatherView : BaseView {

    fun onLoadWeatherSuccess(w: WeatherInfo?)

    fun onLoadWeatherError(error: String)

    fun onLoadPictureSuccess(r: ResponseBody?)

    fun onLoadPictureError(error: String)
}