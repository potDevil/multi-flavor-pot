package com.example.fuzhihuangcom.kotlin.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.fuzhihuangcom.kotlin.R
import com.example.fuzhihuangcom.kotlin.service.bean.weather.ChinaCityInfo

/**
 * Created by fzh on 2018/1/30.
 */
class ChoiceCityAdapter(data: MutableList<ChinaCityInfo>) : BaseQuickAdapter<ChinaCityInfo, BaseViewHolder>(R.layout.item_city) {
    override fun convert(helper: BaseViewHolder, item: ChinaCityInfo) {
        helper.setText(R.id.tv_name, item.name)
    }
}