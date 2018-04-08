package com.example.fuzhihuangcom.kotlin.service

import com.example.fuzhihuangcom.kotlin.service.bean.BaiduGirlInfo
import com.example.fuzhihuangcom.kotlin.service.bean.Book
import com.example.fuzhihuangcom.kotlin.service.bean.HttpResp
import com.example.fuzhihuangcom.kotlin.service.bean.cate.CateDetailListInfo
import com.example.fuzhihuangcom.kotlin.service.bean.cate.CategoryInfo
import com.example.fuzhihuangcom.kotlin.service.bean.weather.ChinaCityInfo
import com.example.fuzhihuangcom.kotlin.service.bean.weather.LocationInfo
import com.example.fuzhihuangcom.kotlin.service.bean.weather.WeatherInfo
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by fuzhihuang on 2017/10/10.
 */

interface RetrofitService {
    /**
     * 网络请求(测试接口)
     */
    @Headers("url_name:douban")
    @GET("v2/book/search")
    fun getSearchBook(@Query("q") name: String?
                      , @Query("tag") tag: String?
                      , @Query("start") start: Int
                      , @Query("count") count: Int): Observable<Book>

    /**
     * 百度定位(暂未用到)
     * http://api.map.baidu.com/geocoder/v2/?ak=xB2GDLWY76rq7GlwUmTtCmn6rlQwvQnh&location=31.696117,121.958458&output=json
     * xB2GDLWY76rq7GlwUmTtCmn6rlQwvQnh
     * ak=百度key&location=经纬度&output=解析格式(xml/json)
     */
    @Headers("url_name:location")
    @GET("geocoder/v2/?ak=xB2GDLWY76rq7GlwUmTtCmn6rlQwvQnh")
    fun getLocation(@Query("location") location: String?
                    , @Query("output") output: String?): Observable<LocationInfo>

    /**
     * 郭霖API(weather界面)
     * http://guolin.tech/api/weather?cityid=" + weatherId + "&key=bc0418b57b2d4918819d3974ac1285d9 天气api
     * http://guolin.tech/api/bing_pic"每日一图
     * http://guolin.tech/api/china中国所有省份
     */
    @Headers("url_name:guolinApi")
    @GET("api/china")
    fun getChinaProvinces(): Observable<List<ChinaCityInfo>>

    @Headers("url_name:guolinApi")
    @GET("api/china/{provinceNum}")
    fun getChinaCity(@Path("provinceNum") provinceNum: Int): Observable<List<ChinaCityInfo>>

    @Headers("url_name:guolinApi")
    @GET("api/china/{provinceNum}/{cityNum}")
    fun getChinaCounty(@Path("provinceNum") provinceNum: Int
                       , @Path("cityNum") cityNum: Int): Observable<List<ChinaCityInfo>>

    @Headers("url_name:guolinApi")
    @GET("api/weather")
    fun getWeather(@Query("cityid") cityid: String?
                   , @Query("key") key: String?): Observable<WeatherInfo>

    @Headers("url_name:guolinApi")
    @GET("api/bing_pic")
    fun getPicture(): Observable<ResponseBody>

    /**
     * 美食API(cate界面)
     * apikey = 24ea856856364
     * http://apicloud.mob.com/v1/cook/category/query?key=24ea856856364                             菜谱分类标签查询
     * http://apicloud.mob.com/v1/cook/menu/search?key=24ea856856364&cid=0010001007&page=1&size=20  按标签查询菜谱接口
     * http://apicloud.mob.com/v1/cook/menu/query?key=24ea856856364&id=00100010070000000001         菜谱查询接口
     */
    @Headers("url_name:mobApi")
    @GET("v1/cook/category/query?")
    fun getCateCategory(@Query("key") key: String): Observable<HttpResp<CategoryInfo>>

    @Headers("url_name:mobApi")
    @GET("v1/cook/menu/search?")
    fun getCateData(@Query("key") key: String
                    , @Query("cid") cid: String
                    , @Query("page") page: Int
                    , @Query("size") size: Int): Observable<HttpResp<CateDetailListInfo>>

    /**
     * 图片查询(girl界面)
     * 图片所有数据
     * pn=开始条数&rn=显示数量
     */
    @Headers("url_name:image")
    @GET("channel/listjson?")
    fun getGirlData(@Query("pn") pn: Int
                    , @Query("rn") rn: Int
                    , @Query("tag1") tag1: String?
                    , @Query("tag2") tag2: String?
                    , @Query("ie") ie: String): Observable<BaiduGirlInfo>
}
