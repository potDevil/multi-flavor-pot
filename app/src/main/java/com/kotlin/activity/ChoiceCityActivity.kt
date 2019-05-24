package com.kotlin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import com.kotlin.R
import com.kotlin.adapter.ChoiceCityAdapter
import com.kotlin.common.COUNTY_ID
import com.kotlin.common.ERROR_TIP
import com.kotlin.service.bean.OnceEvent
import com.kotlin.service.bean.weather.ChinaCityInfo
import com.kotlin.service.presenter.ChoiceCityPresenter
import com.kotlin.service.view.ChoiceCityView
import com.kotlin.view.TitleView
import kotlinx.android.synthetic.main.activity_choice_city.*
import org.greenrobot.eventbus.EventBus


/**
 * Created by fzh on 2018/1/26.
 */

class ChoiceCityActivity : BaseActivity<ChoiceCityPresenter, ChoiceCityView>() {

    /**
     * 当前选中的级别
     */
    private var currentLevel: Int = 0
    /**
     * 省级
     */
    private val LEVEL_PROVINCE = 0
    /**
     * 市级
     */
    private val LEVEL_CITY = 1
    /**
     * 县级
     */
    private val LEVEL_COUNTY = 2
    /**
     * 当前省级id
     */
    private var provinceId = 0
    /**
     * 当前市区id
     */
    private var cityId = 0
    /**
     * 当前县级id
     */
    private var countyWeatherId: String? = null

    private var titleText: TitleView? = null
    private var adapter: ChoiceCityAdapter? = null
    private var provinceInfos: List<ChinaCityInfo> = ArrayList()
    private var cityInfos: List<ChinaCityInfo> = ArrayList()

    private var choiceCityView = object : ChoiceCityView {
        override fun onProvinceSuccess(cityInfos: List<ChinaCityInfo>?) {
            titleText?.setTextTitle("省份")
            (provinceInfos as MutableList).clear()
            cityInfos?.let { (provinceInfos as MutableList).addAll(it) }
            currentLevel = LEVEL_PROVINCE
            onLoadSuccess(cityInfos)
        }

        override fun onProvinceError(error: String?) {
            onLoadError()
        }

        override fun onCitySuccess(cityInfos: List<ChinaCityInfo>?) {
            titleText?.setTextTitle("市区")
            (this@ChoiceCityActivity.cityInfos as MutableList).clear()
            cityInfos?.let { (this@ChoiceCityActivity.cityInfos as MutableList).addAll(it) }
            currentLevel = LEVEL_CITY
            onLoadSuccess(cityInfos)
        }

        override fun onCityError(error: String?) {
            onLoadError()
        }

        override fun onCountySuccess(cityInfos: List<ChinaCityInfo>?) {
            titleText?.setTextTitle("县级")
            currentLevel = LEVEL_COUNTY
            onLoadSuccess(cityInfos)
        }

        override fun onCountyError(error: String?) {
            onLoadError()
        }
    }

    private fun onLoadSuccess(cityInfos: List<ChinaCityInfo>?) {
        refreshLayout.finishRefresh()
        adapter?.setNewData(cityInfos as MutableList<ChinaCityInfo>)
    }

    private fun onLoadError() {
        refreshLayout.finishRefresh(false)  // 结束刷新（刷新失败）
        showToast(ERROR_TIP)
    }

    override fun initRequest() {
        basePresenter = ChoiceCityPresenter(this)
        baseView = choiceCityView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_city)
        getCityInfo()
        initView()
        initRecycleView()
        refreshView()
        itemOnclickListener()
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ChoiceCityActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun getCityInfo() {
        basePresenter?.getProvincesInfo()
    }

    private fun initView() {
        titleText = findViewById(R.id.title) as TitleView
        titleText?.setOnClickListener{
            EventBus.getDefault().post(OnceEvent())
        }
    }

    private fun initRecycleView() {
        adapter = ChoiceCityAdapter()
        rv_choice_city.layoutManager = LinearLayoutManager(this)
        rv_choice_city.adapter = adapter
    }

    private fun refreshView() {
        refreshLayout.setOnRefreshListener { getCityInfo() }
    }

    private fun itemOnclickListener() {
        adapter?.setOnItemClickListener { adapter, _, position ->
            when (currentLevel) {
                LEVEL_PROVINCE -> {
                    Handler().postDelayed({
                        provinceId = (adapter.getItem(position) as ChinaCityInfo).id
                        basePresenter?.getCityInfo(provinceId)
                    }, 500L)
                }
                LEVEL_CITY -> {
                    Handler().postDelayed({
                        cityId = (adapter.getItem(position) as ChinaCityInfo).id
                        basePresenter?.getCountyInfo(provinceId, cityId)
                    }, 500L)
                }
                LEVEL_COUNTY -> Handler().postDelayed({
                    countyWeatherId = (adapter.getItem(position) as ChinaCityInfo).weather_id
                    val intent = Intent()
                    intent.putExtra(COUNTY_ID, countyWeatherId)
                    setResult(RESULT_OK, intent)
                    finish()
                }, 500L)
            }
        }
    }

    override fun onBackPressed() {
        when (currentLevel) {
            LEVEL_PROVINCE ->
                finish()
            LEVEL_CITY -> {
                adapter?.setNewData(provinceInfos)
                currentLevel = LEVEL_PROVINCE
                titleText?.setTextTitle("省份")
            }
            LEVEL_COUNTY -> {
                adapter?.setNewData(cityInfos)
                currentLevel = LEVEL_CITY
                titleText?.setTextTitle("市区")
            }
        }
    }
}
