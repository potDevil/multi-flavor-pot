package com.example.fuzhihuangcom.kotlin.adapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.fuzhihuangcom.kotlin.R
import com.example.fuzhihuangcom.kotlin.common.Constants.Companion.PICTURE_URL
import com.example.fuzhihuangcom.kotlin.service.bean.cate.CateDetailListInfo
import com.example.fuzhihuangcom.kotlin.utils.imageutils.GlideAvaUtil

/**
 * Created by fzh on 2018/4/2.
 */
class CateListAdapter : BaseQuickAdapter<CateDetailListInfo.ListBean, BaseViewHolder>(R.layout.item_cate_list) {
    override fun convert(helper: BaseViewHolder, item: CateDetailListInfo.ListBean) {
        GlideAvaUtil.initGlide(mContext)
        if (!TextUtils.isEmpty(item.thumbnail))
            GlideAvaUtil.loadImage(item.recipe.img, helper.getView(R.id.iv_food_icon))
        else
            GlideAvaUtil.loadImage(PICTURE_URL, helper.getView(R.id.iv_food_icon))
        helper.setText(R.id.tv_food_name, item.name)
                .setText(R.id.tv_food_category, item.ctgTitles)
    }
}