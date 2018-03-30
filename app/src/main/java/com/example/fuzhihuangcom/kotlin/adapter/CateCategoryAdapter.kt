package com.example.fuzhihuangcom.kotlin.adapter

import android.support.v4.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.fuzhihuangcom.kotlin.R
import com.example.fuzhihuangcom.kotlin.service.bean.cate.CategoryInfo

/**
 * Created by fzh on 2018/1/23.
 */
class CateCategoryAdapter : BaseQuickAdapter<CategoryInfo.ResultBean.ChildsBeanX, BaseViewHolder>(R.layout.item_cate_category) {

    override fun convert(helper: BaseViewHolder, item: CategoryInfo.ResultBean.ChildsBeanX) {
        helper.setText(R.id.tv_cate_category, item.categoryInfo.name.replace("按", "").replace("选择菜谱", ""))
        if (item.categoryInfo.select)
            helper.setBackgroundColor(R.id.ll_cate_category, ContextCompat.getColor(mContext, R.color.cate_background))
        else
            helper.setBackgroundColor(R.id.ll_cate_category, ContextCompat.getColor(mContext, R.color.black_alpha_light))
    }
}