package com.kotlin.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.R
import com.kotlin.service.bean.cate.CateRecipeMethod
import com.kotlin.utils.imageutils.GlideAvaUtil

/**
 * Created by fzh on 2018/4/2.
 */
class CateCookingAdapter : BaseQuickAdapter<CateRecipeMethod, BaseViewHolder>(R.layout.item_cate_cooking) {
    override fun convert(helper: BaseViewHolder, item: CateRecipeMethod) {
        GlideAvaUtil.initGlide(mContext)
        if (item.img != null) {
            GlideAvaUtil.loadImage(item.img, helper.getView(R.id.iv_method_icon))
            helper.setGone(R.id.tv_white, false)
        } else {
            helper.setGone(R.id.iv_method_icon, false)
                    .setGone(R.id.tv_white, true)
        }
        if (item.step != null)
            helper.setText(R.id.tv_method_name, item.step)
    }
}