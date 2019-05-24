package com.kotlin.service.presenter

import android.content.Context
import com.kotlin.service.bean.weather.WeatherInfo
import com.kotlin.service.view.BaseView
import com.kotlin.service.view.WeatherView
import okhttp3.ResponseBody
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by fzh on 2018/1/31.
 */
class WeatherPresenter : BasePresenter {
    lateinit var weatherView: WeatherView

    constructor(context: Context) {
        this.context = context
    }

    override fun attachView(baseView: BaseView?) {
        super.attachView(baseView)
        weatherView = baseView as WeatherView
    }

    fun getWeatherInfo(weatherId: String?) {
        compositeSubscription.add(manager.getWeatherInfo(weatherId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<WeatherInfo>() {
                    override fun onNext(w: WeatherInfo?) {
                        weatherView.onLoadWeatherSuccess(w)
                    }

                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {
                        weatherView.onLoadWeatherError(e.toString())
                    }

                }))
    }

    fun getPicture() {
        compositeSubscription.add(manager.getPictureUrl()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ResponseBody>() {
                    override fun onNext(r: ResponseBody?) {
                        weatherView.onLoadPictureSuccess(r)
                    }

                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {
                        weatherView.onLoadPictureError(e.toString())
                    }

                }))
    }
}