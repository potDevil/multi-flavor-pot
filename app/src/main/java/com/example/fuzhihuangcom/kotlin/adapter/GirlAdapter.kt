package com.example.fuzhihuangcom.kotlin.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.fuzhihuangcom.kotlin.R
import com.example.fuzhihuangcom.kotlin.service.bean.BaiduGirlInfo
import com.example.fuzhihuangcom.kotlin.utils.imageutils.GlideAvaUtil

/**
 * Created by fzh on 2018/1/23.
 */
class GirlAdapter(data: MutableList<BaiduGirlInfo.DataBean>?) : BaseQuickAdapter<BaiduGirlInfo.DataBean, BaseViewHolder>(R.layout.item_gril) {

    override fun convert(helper: BaseViewHolder, item: BaiduGirlInfo.DataBean) {
        GlideAvaUtil.initGlide(mContext)
        if (item.image_url != null)
            GlideAvaUtil.loadImage(item.image_url, helper.getView(R.id.iv_girl))
    }
}