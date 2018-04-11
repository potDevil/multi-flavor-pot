package com.example.fuzhihuangcom.kotlin.service.presenter

import android.content.Context
import com.example.fuzhihuangcom.kotlin.service.bean.weather.LocationInfo
import com.example.fuzhihuangcom.kotlin.service.view.LocationView
import com.example.fuzhihuangcom.kotlin.service.view.View
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by fzh on 2018/1/26.
 */
class LocationPresenter : BasePresenter {
    lateinit var locationView: LocationView

    constructor(context: Context) {
        this.context = context
    }
// http://api.map.baidu.com/geocoder/v2/?ak=xB2GDLWY76rq7GlwUmTtCmn6rlQwvQnh&location=31.696117,121.958458&output=json&pois=1
    override fun attachView(view: View) {
        super.attachView(view)
        locationView = view as LocationView
    }

    fun getLocationInfo(location: String?) {
        compositeSubscription.add(manager.getLocationInfo(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<LocationInfo>() {
                    override fun onCompleted() {

                    }

                    override fun onNext(t: LocationInfo?) {
                        locationView.onSuccess(t!!)
                    }

                    override fun onError(e: Throwable?) {
                        locationView.onError(e.toString())
                    }

                }))
    }
}