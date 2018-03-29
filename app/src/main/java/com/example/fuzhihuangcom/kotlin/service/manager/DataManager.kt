package com.example.fuzhihuangcom.kotlin.service.manager

import com.example.fuzhihuangcom.kotlin.service.RetrofitHelper
import com.example.fuzhihuangcom.kotlin.service.RetrofitService
import com.example.fuzhihuangcom.kotlin.service.bean.BaiduGirlInfo
import com.example.fuzhihuangcom.kotlin.service.bean.Book
import com.example.fuzhihuangcom.kotlin.service.bean.weatherinfo.ChinaCityInfo
import com.example.fuzhihuangcom.kotlin.service.bean.weatherinfo.LocationInfo
import com.example.fuzhihuangcom.kotlin.service.bean.weatherinfo.WeatherInfo
import okhttp3.ResponseBody
import rx.Observable

/**
 * Created by fzh on 2018/1/19.
 */
class DataManager {
    private var mRetrofitService: RetrofitService = RetrofitHelper.server

//    constructor(context: Context) {
//
//    }

    fun getSearchBooks(name: String?, tag: String?, start: Int, count: Int): Observable<Book> {
        return mRetrofitService.getSearchBook(name, tag, start, count)
    }

    fun getGirlInfo(num: Int): Observable<BaiduGirlInfo> {
        return mRetrofitService.getGirlData(num, 10, "美女", "全部", "utf8")
    }

    fun getLocationInfo(location: String?): Observable<LocationInfo> {
        return mRetrofitService.getLocation(location, "json")
    }

    fun getProvinceInfo(): Observable<List<ChinaCityInfo>> {
        return mRetrofitService.getChinaProvinces()
    }

    fun getCityInfo(provinceNum: Int): Observable<List<ChinaCityInfo>> {
        return mRetrofitService.getChinaCity(provinceNum)
    }

    fun getCountyInfo(provinceNum: Int, cityNum: Int): Observable<List<ChinaCityInfo>> {
        return mRetrofitService.getChinaCounty(provinceNum, cityNum)
    }

    fun getWeatherInfo(cityId: String?): Observable<WeatherInfo> {
        return mRetrofitService.getWeather(cityId, "bc0418b57b2d4918819d3974ac1285d9")
    }

    fun getPictureUrl(): Observable<ResponseBody> {
        return mRetrofitService.getPicture()
    }
}