package com.example.fuzhihuangcom.kotlin.service.presenter

import android.content.Context
import com.example.fuzhihuangcom.kotlin.service.bean.weatherinfo.WeatherInfo
import com.example.fuzhihuangcom.kotlin.service.view.View
import com.example.fuzhihuangcom.kotlin.service.view.WeatherView
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
        mContext = context
    }

    override fun attachView(view: View) {
        super.attachView(view)
        weatherView = view as WeatherView
    }

    fun getWeatherInfo(weatherId: String?) {
        mCompositeSubscription.add(mManager.getWeatherInfo(weatherId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<WeatherInfo>() {
                    override fun onNext(w: WeatherInfo?) {
                        weatherView.onLoadWeatherSuccess(w!!)
                    }

                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {
                        weatherView.onLoadWeatherError(e.toString())
                    }

                }))
    }

    fun getPicture() {
        mCompositeSubscription.add(mManager.getPictureUrl()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ResponseBody>() {
                    override fun onNext(r: ResponseBody?) {
                        weatherView.onLoadPictureSuccess(r!!)
                    }

                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {
                        weatherView.onLoadPictureError(e.toString())
                    }

                }))
    }
}