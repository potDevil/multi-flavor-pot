@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.fuzhihuangcom.kotlin.service

import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by fuzhihuang on 2017/10/10.
 */

object RetrofitHelper {

    private val BASE_URL_BAIDU = "http://image.baidu.com/"
    private val BASE_URL_DOUBAN = "https://api.douban.com/v2/"
    private val BASE_URL_LOCATION = "http://api.map.baidu.com/"
    private val BASE_URL_WEATHER = "http://guolin.tech/"
    private val BASE_URL_CATE = "http://apicloud.mob.com/v1/"

    private val client: OkHttpClient by lazy { createOkHttpClient() }

    private val retrofit: Retrofit by lazy { createRetrofit() }

    val server: RetrofitService by lazy { retrofit.create(RetrofitService::class.java) }

    private var factory: GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_BAIDU)
                .client(client)
                .addConverterFactory(factory)
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        // 监听访问内容(url)
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient()
                .newBuilder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(Interceptor { chain ->
                    // 获取request
                    val request: Request = chain.request()
                    // 从request中获取原有的HttpUrl实例oldHttpUrl
                    val oldHttpUrl = request.url()
                    // 获取request的创建者builder
                    val builder: Request.Builder = request.newBuilder()
                    // 从request中获取headers，通过给定的键url_name
                    val headerValues: MutableList<String> = request.headers("url_name")
                    if (headerValues.size > 0) {
                        //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
                        builder.removeHeader("url_name")
                        //匹配获得新的BaseUrl
                        val headerValue: String = headerValues[0]
                        val newBaseUrl: HttpUrl?
                        newBaseUrl = when (headerValue) {
                            "image" -> HttpUrl.parse(BASE_URL_BAIDU)
                            "douban" -> HttpUrl.parse(BASE_URL_DOUBAN)
                            "location" -> HttpUrl.parse(BASE_URL_LOCATION)
                            "guolinApi" -> HttpUrl.parse(BASE_URL_WEATHER)
                            "mobApi" -> HttpUrl.parse(BASE_URL_CATE)
                            else -> oldHttpUrl
                        }
                        // 重建新的HttpUrl，修改需要修改的url部分
                        val newFullUrl: HttpUrl = oldHttpUrl
                                .newBuilder()
                                // .scheme("https")          // 更换网络协议
                                .host(newBaseUrl?.host())      // 更换主机名
                                .port(newBaseUrl?.port() ?: 0)     // 更换端口
                                //  .removePathSegment(0)    // 移除第一个参数
                                .build()
                        return@Interceptor chain.proceed(builder.url(newFullUrl).build())
                    }
                    chain.proceed(request)
                })

        return client.build()
    }
}
