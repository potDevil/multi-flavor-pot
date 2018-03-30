package com.example.fuzhihuangcom.kotlin.service.presenter

import android.content.Context
import com.example.fuzhihuangcom.kotlin.service.bean.weather.ChinaCityInfo
import com.example.fuzhihuangcom.kotlin.service.view.ChoiceCityView
import com.example.fuzhihuangcom.kotlin.service.view.View
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by fzh on 2018/1/30.
 */
class ChoiceCityPresenter : BasePresenter {

    private lateinit var choiceCityView: ChoiceCityView

    constructor(context: Context) {
        mContext = context
    }

    override fun attachView(view: View) {
        super.attachView(view)
        choiceCityView = view as ChoiceCityView
    }

    fun getProvincesInfo() {
        mCompositeSubscription.add(mManager.getProvinceInfo()
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
        mCompositeSubscription.add(mManager.getCityInfo(provinceNum)
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
        mCompositeSubscription.add(mManager.getCountyInfo(provinceNum, cityNum)
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