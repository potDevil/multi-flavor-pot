package com.example.fuzhihuangcom.kotlin.service.view

import com.example.fuzhihuangcom.kotlin.service.bean.weatherinfo.WeatherInfo
import okhttp3.ResponseBody

/**
 * Created by fzh on 2018/1/31.
 */
interface WeatherView : View {

    fun onLoadWeatherSuccess(w: WeatherInfo?)

    fun onLoadWeatherError(error: String)

    fun onLoadPictureSuccess(r: ResponseBody)

    fun onLoadPictureError(error: String)
}