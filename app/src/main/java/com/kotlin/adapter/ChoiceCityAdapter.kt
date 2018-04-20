package com.kotlin.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.R
import com.kotlin.service.bean.weather.ChinaCityInfo

/**
 * Created by fzh on 2018/1/30.
 */
class ChoiceCityAdapter : BaseQuickAdapter<ChinaCityInfo, BaseViewHolder>(R.layout.item_city) {
    override fun convert(helper: BaseViewHolder, item: ChinaCityInfo) {
        helper.setText(R.id.tv_name, item.name)
    }
}