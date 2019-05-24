package com.kotlin.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.kotlin.R
import com.kotlin.activity.WeChatCategoryActivity
import com.kotlin.adapter.WeChatPageAdapter
import com.kotlin.common.USER_CATEGORY
import com.kotlin.common.WECHAT_USER_INFO
import com.kotlin.service.bean.wechat.WeChatCategoryInfo
import com.kotlin.service.presenter.BasePresenter
import com.kotlin.service.view.BaseView
import com.kotlin.utils.AndroidUtils
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
class WeChatChoicenessFragment : BaseLazyFragment<BasePresenter, BaseView>(), ViewPager.OnPageChangeListener, View.OnClickListener {

    companion object {
        const val REQUEST_WECHAT_ITEM = 1
    }

    private var iv_add: ImageView? = null
    private var magicIndicator: MagicIndicator? = null
    private var commonNavigator: CommonNavigator? = null
    private var weChatPageAdapter: WeChatPageAdapter? = null

    private var userCategoryList = ArrayList<WeChatCategoryInfo>()

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_add -> {
                val intent = Intent(context, WeChatCategoryActivity().javaClass)
                intent.putExtra(USER_CATEGORY, userCategoryList)
                startActivityForResult(intent, REQUEST_WECHAT_ITEM)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_WECHAT_ITEM && resultCode == Activity.RESULT_OK) {
            initTitleItem()
            initIndicator()
        }
    }

    override fun initRequest() {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(context, R.layout.fragment_wechat_choiceness, null)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTitleItem()
        initView(view)
        initIndicator()
    }

    private fun initTitleItem() {
        if (Hawk.get<ArrayList<WeChatCategoryInfo>>(WECHAT_USER_INFO, ArrayList()).isNotEmpty()) {
            userCategoryList = Hawk.get<ArrayList<WeChatCategoryInfo>>(WECHAT_USER_INFO, ArrayList())
        } else {
            var weChatCategory: WeChatCategoryInfo?
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "时尚"
            weChatCategory.cid = "1"
            userCategoryList.add(weChatCategory)
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "热点"
            weChatCategory.cid = "2"
            userCategoryList.add(weChatCategory)
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "健康"
            weChatCategory.cid = "3"
            userCategoryList.add(weChatCategory)
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "百科"
            weChatCategory.cid = "5"
            userCategoryList.add(weChatCategory)
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "娱乐"
            weChatCategory.cid = "7"
            userCategoryList.add(weChatCategory)
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "美文"
            weChatCategory.cid = "8"
            userCategoryList.add(weChatCategory)
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "旅行"
            weChatCategory.cid = "9"
            userCategoryList.add(weChatCategory)
            weChatCategory = WeChatCategoryInfo()
            weChatCategory.name = "媒体达人"
            weChatCategory.cid = "10"
            userCategoryList.add(weChatCategory)

            Hawk.put(WECHAT_USER_INFO, userCategoryList)
        }
    }

    private fun initView(view: View?) {
        magicIndicator = view?.findViewById(R.id.magic_indicator)
        iv_add = view?.findViewById(R.id.iv_add)
        iv_add?.setOnClickListener(this)
    }

    private fun initIndicator() {
        commonNavigator = CommonNavigator(context)
        commonNavigator?.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return userCategoryList.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
                colorTransitionPagerTitleView.normalColor = Color.WHITE
                colorTransitionPagerTitleView.selectedColor = ContextCompat.getColor(context, R.color.blue_indicator)
                colorTransitionPagerTitleView.textSize = AndroidUtils.dip2px(context, 5f).toFloat()
                colorTransitionPagerTitleView.text = userCategoryList[index].name
                colorTransitionPagerTitleView.setOnClickListener { view_pager.currentItem = index }
                return colorTransitionPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = TriangularPagerIndicator(context)
                indicator.lineColor = ContextCompat.getColor(context, R.color.blue_indicator)
                return indicator
            }
        }
        magicIndicator?.navigator = commonNavigator
        ViewPagerHelper.bind(magicIndicator, view_pager)
        weChatPageAdapter = WeChatPageAdapter(fragmentManager, userCategoryList)
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

    }
}