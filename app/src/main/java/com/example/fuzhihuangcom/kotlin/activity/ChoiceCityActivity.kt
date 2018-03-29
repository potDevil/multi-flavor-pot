package com.example.fuzhihuangcom.kotlin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import com.example.fuzhihuangcom.kotlin.R
import com.example.fuzhihuangcom.kotlin.adapter.ChoiceCityAdapter
import com.example.fuzhihuangcom.kotlin.common.Constants.Companion.COUNTY_ID
import com.example.fuzhihuangcom.kotlin.service.bean.weatherinfo.ChinaCityInfo
import com.example.fuzhihuangcom.kotlin.service.presenter.ChoiceCityPresenter
import com.example.fuzhihuangcom.kotlin.service.view.ChoiceCityView
import com.example.fuzhihuangcom.kotlin.view.TitleView
import kotlinx.android.synthetic.main.activity_choice_city.*


/**
 * Created by fzh on 2018/1/26.
 */

class ChoiceCityActivity : BaseActivity() {

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
    private var mProvinceInfos: List<ChinaCityInfo> = ArrayList<ChinaCityInfo>()
    private var mCityInfos: List<ChinaCityInfo> = ArrayList<ChinaCityInfo>()

    private lateinit var choiceCityPresenter: ChoiceCityPresenter
    private var mChoiceCityView: ChoiceCityView = object : ChoiceCityView {
        override fun onProvinceSuccess(cityInfos: List<ChinaCityInfo>) {
            titleText?.setTextTitle("省份")
            (mProvinceInfos as MutableList).clear()
            (mProvinceInfos as MutableList).addAll(cityInfos)
            currentLevel = LEVEL_PROVINCE
            onLoadSuccess(cityInfos)
        }

        override fun onProvinceError(error: String) {
            onLoadError()
        }

        override fun onCitySuccess(cityInfos: List<ChinaCityInfo>) {
            titleText?.setTextTitle("市区")
            (mCityInfos as MutableList).clear()
            (mCityInfos as MutableList).addAll(cityInfos)
            currentLevel = LEVEL_CITY
            onLoadSuccess(cityInfos)
        }

        override fun onCityError(error: String) {
            onLoadError()
        }

        override fun onCountySuccess(cityInfos: List<ChinaCityInfo>) {
            titleText?.setTextTitle("县级")
            currentLevel = LEVEL_COUNTY
            onLoadSuccess(cityInfos)
        }

        override fun onCountyError(error: String) {
            onLoadError()
        }
    }

    private fun onLoadSuccess(cityInfos: List<ChinaCityInfo>) {
        refreshLayout.finishRefresh()
        adapter?.setNewData(cityInfos as MutableList<ChinaCityInfo>)
    }

    private fun onLoadError() {
        refreshLayout.finishRefresh(false)  // 结束刷新（刷新失败）
        showToast("网络故障~亲！")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_city)
        bindRequest()
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

    private fun bindRequest() {
        choiceCityPresenter = ChoiceCityPresenter(this)
        choiceCityPresenter.onCreate()
        choiceCityPresenter.attachView(mChoiceCityView)
    }

    private fun getCityInfo() {
        choiceCityPresenter.getProvincesInfo()
    }

    private fun initView() {
        titleText = findViewById(R.id.title)
    }

    private fun initRecycleView() {
        adapter = ChoiceCityAdapter(mCityInfos as MutableList<ChinaCityInfo>)
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
                        choiceCityPresenter.getCityInfo(provinceId)
                    }, 500L)
                }
                LEVEL_CITY -> {
                    Handler().postDelayed({
                        cityId = (adapter.getItem(position) as ChinaCityInfo).id
                        choiceCityPresenter.getCountyInfo(provinceId, cityId)
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
                adapter?.setNewData(mProvinceInfos)
                currentLevel = LEVEL_PROVINCE
                titleText?.setTextTitle("省份")
            }
            LEVEL_COUNTY -> {
                adapter?.setNewData(mCityInfos)
                currentLevel = LEVEL_CITY
                titleText?.setTextTitle("市区")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        choiceCityPresenter.onStop()
    }
}
