package com.kotlin.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.R
import com.kotlin.service.bean.cate.CategoryInfo

/**
 * Created by fzh on 2018/1/23.
 */
class CateDetailAdapter : BaseQuickAdapter<CategoryInfo.ChildsBeanX.ChildsBean, BaseViewHolder>(R.layout.item_cate_detail) {

    override fun convert(helper: BaseViewHolder, item: CategoryInfo.ChildsBeanX.ChildsBean) {
        helper.setText(R.id.tv_cate_detail, item.categoryInfo.name)
    }
}