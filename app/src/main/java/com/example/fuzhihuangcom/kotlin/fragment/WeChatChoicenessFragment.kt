package com.example.fuzhihuangcom.kotlin.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fuzhihuangcom.kotlin.R
import com.example.fuzhihuangcom.kotlin.adapter.WeChatPageAdapter
import com.example.fuzhihuangcom.kotlin.common.Constants.Companion.ERROR_TIP
import com.example.fuzhihuangcom.kotlin.common.Constants.Companion.WECHAT_CATEGORY_INFO
import com.example.fuzhihuangcom.kotlin.service.bean.wechat.WeChatCategoryInfo
import com.example.fuzhihuangcom.kotlin.service.presenter.WeChatCategoryPresenter
import com.example.fuzhihuangcom.kotlin.service.view.WeChatCategoryView
import com.example.fuzhihuangcom.kotlin.utils.AndroidUtils
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_wechat_choiceness.*
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.TriangularPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView


/**
 * Created by fzh on 2018/4/8.
 */

class WeChatChoicenessFragment : BaseLazyFragment(), ViewPager.OnPageChangeListener {

    private lateinit var weChatCategoryPresenter: WeChatCategoryPresenter
    private var magicIndicator: MagicIndicator? = null
    private var commonNavigator: CommonNavigator? = null
    private var titleDataList: ArrayList<WeChatCategoryInfo> = ArrayList()
    private var weChatPageAdapter: WeChatPageAdapter? = null

    private var weChatCategoryView = object : WeChatCategoryView {
        override fun onLoadWeChatDataSuccess(t: List<WeChatCategoryInfo>?) {

            Hawk.put<List<WeChatCategoryInfo>>(WECHAT_CATEGORY_INFO, t)
        }

        override fun onLoadWeChatDataError(s: String?) {
            showToast(ERROR_TIP)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(context, R.layout.fragment_wechat_choiceness, null)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindRequest()
        initTitleData()
        initView(view)
    }

    private fun bindRequest() {
        weChatCategoryPresenter = WeChatCategoryPresenter(context)
        weChatCategoryPresenter.onCreate()
        weChatCategoryPresenter.attachView(weChatCategoryView)
    }

    private fun initTitleData() {
        if (!Hawk.get<ArrayList<WeChatCategoryInfo>>(WECHAT_CATEGORY_INFO, ArrayList()).isEmpty()) {
            val weChatCacheInfo = Hawk.get<ArrayList<WeChatCategoryInfo>>(WECHAT_CATEGORY_INFO, ArrayList())
            (0 until weChatCacheInfo.size)
                    .filter { it < 8 }
                    .forEach { titleDataList.add(weChatCacheInfo[it]) }
        } else {
            var weChatCategory: WeChatCategoryInfo?
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "时尚"
            weChatCategory.cid = "1"
            titleDataList.add(weChatCategory)
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "热点"
            weChatCategory.cid = "2"
            titleDataList.add(weChatCategory)
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "健康"
            weChatCategory.cid = "3"
            titleDataList.add(weChatCategory)
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "百科"
            weChatCategory.cid = "5"
            titleDataList.add(weChatCategory)
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "娱乐"
            weChatCategory.cid = "7"
            titleDataList.add(weChatCategory)
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "美文"
            weChatCategory.cid = "8"
            titleDataList.add(weChatCategory)
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "旅行"
            weChatCategory.cid = "9"
            titleDataList.add(weChatCategory)
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "媒体达人"
            weChatCategory.cid = "10"
            titleDataList.add(weChatCategory)
        }
    }

    private fun initView(view: View?) {
        magicIndicator = view?.findViewById(R.id.magic_indicator)
        commonNavigator = CommonNavigator(context)
        commonNavigator?.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return titleDataList.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
                colorTransitionPagerTitleView.normalColor = Color.WHITE
                colorTransitionPagerTitleView.selectedColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)
                colorTransitionPagerTitleView.textSize = AndroidUtils.dip2px(context, 5f).toFloat()
                colorTransitionPagerTitleView.text = titleDataList[index].name
                colorTransitionPagerTitleView.setOnClickListener { view_pager.currentItem = index }
                return colorTransitionPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = TriangularPagerIndicator(context)
                indicator.lineColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)
                return indicator
            }
        }
        magicIndicator?.navigator = commonNavigator
        ViewPagerHelper.bind(magicIndicator, view_pager)
        weChatPageAdapter = WeChatPageAdapter(fragmentManager, titleDataList)
        view_pager.addOnPageChangeListener(this)
        view_pager.adapter = weChatPageAdapter
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        magicIndicator?.onPageScrolled(position, positionOffset, positionOffsetPixels)
    }

    override fun onPageSelected(position: Int) {
        magicIndicator?.onPageSelected(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
        magicIndicator?.onPageScrollStateChanged(state)
    }

    override fun lazyLoadData() {
        weChatCategoryPresenter.getWeChatCategory()
    }

    override fun onDestroy() {
        super.onDestroy()
        weChatCategoryPresenter.onStop()
    }
}