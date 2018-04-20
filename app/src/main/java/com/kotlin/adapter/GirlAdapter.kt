package com.kotlin.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.R
import com.kotlin.service.bean.BaiduGirlInfo
import com.kotlin.utils.imageutils.GlideAvaUtil

/**
 * Created by fzh on 2018/1/23.
 */
class GirlAdapter : BaseQuickAdapter<BaiduGirlInfo.DataBean, BaseViewHolder>(R.layout.item_gril) {

    override fun convert(helper: BaseViewHolder, item: BaiduGirlInfo.DataBean) {
        GlideAvaUtil.initGlide(mContext)
        if (item.image_url != null)
            GlideAvaUtil.loadImage(item.image_url, helper.getView(R.id.iv_girl))
    }
}