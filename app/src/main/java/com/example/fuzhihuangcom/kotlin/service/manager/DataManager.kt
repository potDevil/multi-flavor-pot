package com.example.fuzhihuangcom.kotlin.service.manager

import com.example.fuzhihuangcom.kotlin.common.CATE_KEY
import com.example.fuzhihuangcom.kotlin.service.RetrofitHelper
import com.example.fuzhihuangcom.kotlin.service.RetrofitService
import com.example.fuzhihuangcom.kotlin.service.bean.BaiduGirlInfo
import com.example.fuzhihuangcom.kotlin.service.bean.Book
import com.example.fuzhihuangcom.kotlin.service.bean.HttpListResp
import com.example.fuzhihuangcom.kotlin.service.bean.HttpResp
import com.example.fuzhihuangcom.kotlin.service.bean.cate.CateDetailListInfo
import com.example.fuzhihuangcom.kotlin.service.bean.cate.CategoryInfo
import com.example.fuzhihuangcom.kotlin.service.bean.weather.ChinaCityInfo
import com.example.fuzhihuangcom.kotlin.service.bean.weather.LocationInfo
import com.example.fuzhihuangcom.kotlin.service.bean.weather.WeatherInfo
import com.example.fuzhihuangcom.kotlin.service.bean.wechat.WeChatCategoryInfo
import com.example.fuzhihuangcom.kotlin.service.bean.wechat.WeChatListInfo
import okhttp3.ResponseBody
import rx.Observable

/**
 * Created by fzh on 2018/1/19.
 */
class DataManager {
    private var retrofitService: RetrofitService = RetrofitHelper.server

//    constructor(context: Context) {
//
//    }

    fun getSearchBooks(name: String?, tag: String?, start: Int, count: Int): Observable<Book> {
        return retrofitService.getSearchBook(name, tag, start, count)
    }

    fun getLocationInfo(location: String?): Observable<LocationInfo> {
        return retrofitService.getLocation(location, "json")
    }

    fun getProvinceInfo(): Observable<List<ChinaCityInfo>> {
        return retrofitService.getChinaProvinces()
    }

    fun getCityInfo(provinceNum: Int): Observable<List<ChinaCityInfo>> {
        return retrofitService.getChinaCity(provinceNum)
    }

    fun getCountyInfo(provinceNum: Int, cityNum: Int): Observable<List<ChinaCityInfo>> {
        return retrofitService.getChinaCounty(provinceNum, cityNum)
    }

    fun getWeatherInfo(cityId: String?): Observable<WeatherInfo> {
        return retrofitService.getWeather(cityId, "bc0418b57b2d4918819d3974ac1285d9")
    }

    fun getPictureUrl(): Observable<ResponseBody> {
        return retrofitService.getPicture()
    }

    fun getCateCategory(): Observable<HttpResp<CategoryInfo>> {
        return retrofitService.getCateCategory(CATE_KEY)
    }

    fun getCateData(cid: String, page: Int, size: Int): Observable<HttpResp<CateDetailListInfo>> {
        return retrofitService.getCateData(CATE_KEY, cid, page, size)
    }

    fun getWeChatCategory(): Observable<HttpListResp<WeChatCategoryInfo>> {
        return retrofitService.getWeChatCategory(CATE_KEY)
    }

    fun getWeChatList(cid: String, page: Int, size: Int): Observable<HttpResp<WeChatListInfo>> {
        return retrofitService.getWeChatList(CATE_KEY, cid, page, size)
    }

    fun getGirlInfo(num: Int): Observable<BaiduGirlInfo> {
        return retrofitService.getGirlData(num, 10, "美女", "全部", "utf8")
    }
}