package com.kotlin.service.presenter

import android.content.Context
import com.kotlin.service.bean.weather.ChinaCityInfo
import com.kotlin.service.view.ChoiceCityView
import com.kotlin.service.view.BaseView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by fzh on 2018/1/30.
 */
class ChoiceCityPresenter : BasePresenter {

    private lateinit var choiceCityView: ChoiceCityView

    constructor(context: Context) {
        this.context = context
    }

    override fun attachView(baseView: BaseView?) {
        super.attachView(baseView)
        choiceCityView = baseView as ChoiceCityView
    }

    fun getProvincesInfo() {
        compositeSubscription.add(manager.getProvinceInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<List<ChinaCityInfo>>() {
                    override fun onCompleted() {

                    }

                    override fun onNext(t: List<ChinaCityInfo>?) {
                        choiceCityView.onProvinceSuccess(t)
                    }

                    override fun onError(e: Throwable?) {
                        choiceCityView.onProvinceError(e.toString())
                    }
                }))
    }

    fun getCityInfo(provinceNum: Int) {
        compositeSubscription.add(manager.getCityInfo(provinceNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<List<ChinaCityInfo>>() {
                    override fun onCompleted() {

                    }

                    override fun onNext(t: List<ChinaCityInfo>?) {
                        choiceCityView.onCitySuccess(t)
                    }

                    override fun onError(e: Throwable?) {
                        choiceCityView.onCityError(e.toString())
                    }

                }))
    }

    fun getCountyInfo(provinceNum: Int, cityNum: Int) {
        compositeSubscription.add(manager.getCountyInfo(provinceNum, cityNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<List<ChinaCityInfo>>() {
                    override fun onCompleted() {

                    }

                    override fun onNext(t: List<ChinaCityInfo>?) {
                        choiceCityView.onCountySuccess(t)
                    }

                    override fun onError(e: Throwable?) {
                        choiceCityView.onCountyError(e.toString())
                    }

                }))
    }
}