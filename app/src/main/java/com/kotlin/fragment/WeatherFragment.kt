package com.kotlin.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.FragmentActivity
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlin.R
import com.kotlin.activity.ChoiceCityActivity
import com.kotlin.common.*
import com.kotlin.service.bean.weather.WeatherInfo
import com.kotlin.service.presenter.WeatherPresenter
import com.kotlin.service.view.WeatherView
import com.kotlin.utils.imageutils.GlideAvaUtil
import com.kotlin.view.dialog.AqiDialog
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.aqi.*
import kotlinx.android.synthetic.main.forecast.*
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.now.*
import kotlinx.android.synthetic.main.suggestion.*
import kotlinx.android.synthetic.main.title.*
import okhttp3.ResponseBody

/**
 * Created by fzh on 2018/1/22.
 */
class WeatherFragment : BaseLazyFragment<WeatherPresenter>(), View.OnClickListener {

    private var cityStr: String? = null
    private var heWeatherInfo: WeatherInfo.HeWeatherBean? = null
    private var pictureUrl: String? = null
    private var aqiDialog: AqiDialog? = null
    private var apiNum: String? = null
    private var pmNum: String? = null

    private var weatherView: WeatherView = object : WeatherView {

        override fun onLoadWeatherSuccess(w: WeatherInfo?) {
            basePresenter?.getPicture()
            heWeatherInfo = w?.HeWeather?.get(0)
            Hawk.put(WEATHER_INFO, w)
            setData()
        }

        override fun onLoadWeatherError(error: String) {
            refreshLayout.finishRefresh(false)
            if (Hawk.get<WeatherInfo>(WEATHER_INFO) != null && Hawk.get<String>(PICTURE_INFO) != null) {
                heWeatherInfo = Hawk.get<WeatherInfo>(WEATHER_INFO)?.HeWeather?.get(0)
                pictureUrl = Hawk.get<String>(PICTURE_INFO)
                setData()
                showPicture()
                showToast(ERROR_SHOW_HISTORY_TIP)
            } else {
                showToast(ERROR_TIP)
            }
        }

        override fun onLoadPictureSuccess(r: ResponseBody?) {
            refreshLayout.finishRefresh()
            pictureUrl = r?.string()
            Hawk.put(PICTURE_INFO, pictureUrl)
            showPicture()
        }

        override fun onLoadPictureError(error: String) {
            refreshLayout.finishRefresh(false)
            showToast(ERROR_TIP)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(context, R.layout.fragment_weather, null)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Hawk.init(context).build()
        initView()
        bindRequest()
        getWeatherData()
        setFloatingButton()
    }

    private fun initView() {
        ll_save_city.setOnClickListener(this)
        iv_tips.setOnClickListener(this)
        Handler().postDelayed({
            tv_white_page.visibility = View.GONE
        }, 800L)
        // 下拉刷新
        refreshLayout.setOnRefreshListener { getWeatherData() }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_save_city -> {
                Hawk.put(CITY_NAME, cityStr)
                showToast("save success")
            }
            R.id.iv_tips -> {
                showApiDialog()
            }
        }
    }

    private fun bindRequest() {
        basePresenter = WeatherPresenter(context)
        basePresenter?.onCreate()
        basePresenter?.attachView(weatherView)
    }

    private fun getWeatherData() {
        val saveCity: String = Hawk.get<String>(CITY_NAME, BEIJING_ID)
        cityStr = saveCity
        basePresenter?.getWeatherInfo(saveCity)   // 默认查询Id北京
    }

    private fun setFloatingButton() {
        floatingButton.setOnClickListener {
            val intent = Intent(context, ChoiceCityActivity().javaClass)
            startActivityForResult(intent, 0)
        }
    }

    private fun showApiDialog() {
        if (aqiDialog == null) {
            aqiDialog = AqiDialog(context)
        }
        aqiDialog?.setApiTextColor(apiNum ?: "")
        aqiDialog?.setPmTextColor(pmNum ?: "")
        aqiDialog?.show()
    }

    @SuppressWarnings("All")
    private fun setData() {
        title_city.text = heWeatherInfo?.basic?.city
        title_update_time.text = heWeatherInfo?.basic?.update?.loc?.let { it.split(" ")[1] }
        degree_text.text = heWeatherInfo?.now?.tmp + "℃"
        weather_info_text.text = heWeatherInfo?.now?.cond?.txt
        forecast_layout?.removeAllViews()
        val daily_forecast: MutableList<WeatherInfo.HeWeatherBean.DailyForecastBean> = heWeatherInfo?.daily_forecast as MutableList
        daily_forecast.forEach {
            val view: View = LayoutInflater.from(context).inflate(R.layout.forecast_item, forecast_layout, false)
            val date_text: TextView = view.findViewById(R.id.date_text)
            val info_text: TextView = view.findViewById(R.id.info_text)
            val max_text: TextView = view.findViewById(R.id.max_text)
            val min_text: TextView = view.findViewById(R.id.min_text)
            date_text.text = it.date
            info_text.text = it.cond?.txt_d
            max_text.text = it.tmp?.max
            min_text.text = it.tmp?.min
            forecast_layout?.addView(view)
        }
        apiNum = heWeatherInfo?.aqi?.city?.aqi
        pmNum = heWeatherInfo?.aqi?.city?.pm25
        aqi_text.text = apiNum
        pm25_text.text = pmNum
        comfort_text.text = COMFORT + heWeatherInfo?.suggestion?.comf?.txt
        car_wash_text.text = CAR_WASH + heWeatherInfo?.suggestion?.cw?.txt
        sport_text.text = SPORT + heWeatherInfo?.suggestion?.sport?.txt
        if (heWeatherInfo?.suggestion?.flu?.txt == null) {
            cold_text.text = COLD + NO_DATA
        } else {
            cold_text.text = COLD + heWeatherInfo?.suggestion?.flu?.txt
        }
        forecast_layout?.visibility = View.VISIBLE
    }

    private fun showPicture() {
        GlideAvaUtil.initGlide(context)
        GlideAvaUtil.loadImage(pictureUrl, iv_bg)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            FragmentActivity.RESULT_OK -> {
                if (!TextUtils.isEmpty(data?.extras?.getString(COUNTY_ID))) {
                    cityStr = data?.extras?.getString(COUNTY_ID)
                    basePresenter?.getWeatherInfo(data?.extras?.getString(COUNTY_ID))
                }
            }
        }
    }

    override fun lazyLoadData() {

    }

    override fun onHiddenChanged(hidden: Boolean) {

    }
}