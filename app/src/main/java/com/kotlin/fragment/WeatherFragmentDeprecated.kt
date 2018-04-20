package com.kotlin.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.R
import com.kotlin.activity.ChoiceCityActivity
import com.kotlin.service.bean.weather.LocationInfo
import com.kotlin.service.presenter.LocationPresenter
import com.kotlin.service.view.LocationView
import kotlinx.android.synthetic.main.fragment_weather.*

/**
 * Created by fzh on 2018/1/22.
 */
@Deprecated("locationFragment")
class WeatherFragmentDeprecated : BaseLazyFragment() {

    var locationManager: LocationManager? = null
    var locationProvider: String? = null
    var locationStr: String? = null

    private lateinit var mLocationPresenter: LocationPresenter
    private var mLocationView: LocationView = object : LocationView {
        override fun onSuccess(locationInfos: LocationInfo) {
//            tv1.setText(locationInfos.result.formatted_address.toString())
        }

        override fun onError(result: String) {
//            showToast("网络故障~亲！")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater!!.inflate(R.layout.fragment_weather, container, false)
        mLocationPresenter = LocationPresenter(context)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLocation()
        bindRequest()
        setFloatingButton()
    }

    private fun bindRequest() {
        mLocationPresenter.onCreate()
        mLocationPresenter.attachView(mLocationView)
        if (!TextUtils.isEmpty(locationStr))
            mLocationPresenter.getLocationInfo(locationStr!!)
    }

    @SuppressLint("MissingPermission")
    fun initLocation() {
        // 获取地址位置管理器
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // 获取所有可用的位置提供器
        val providers: MutableList<String>? = locationManager!!.getProviders(true)
        if (providers!!.contains(LocationManager.GPS_PROVIDER)) {
            // 如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            // 如果是NetWork
            locationProvider = LocationManager.NETWORK_PROVIDER
        } else {
            showToast("没有可用的位置提供器")
            return
        }
        // 获取Location
        showLocation()
    }

    @SuppressLint("MissingPermission")
    private fun showLocation() {
        var location: Location = locationManager!!.getLastKnownLocation(locationProvider)
        if (location != null) {
            // 不为空，显示地理位置经纬度
            locationStr = location.latitude.toString() + "," + location.longitude.toString()
        }
        //监视地理位置变化
        locationManager!!.requestLocationUpdates(locationProvider, 3000, 1f, locationListener)
    }

    var locationListener: LocationListener = object : LocationListener {

        override fun onStatusChanged(provider: String, status: Int, arg2: Bundle) {

        }

        override fun onProviderEnabled(provider: String) {

        }

        override fun onProviderDisabled(provider: String) {

        }

        override fun onLocationChanged(location: Location) {
            // 如果地理位置发生变化,重新显示
            showLocation()
            if (!TextUtils.isEmpty(locationStr))
                mLocationPresenter.getLocationInfo(locationStr)
        }
    }

    private fun setFloatingButton() {
        floatingButton.setOnClickListener { _ ->
            ChoiceCityActivity.start(context)
        }
    }

    override fun lazyLoadData() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {  // 显示
            initLocation()
        } else {        // 隐藏
            //移除监听器
            locationManager!!.removeUpdates(locationListener);
        }
    }
}