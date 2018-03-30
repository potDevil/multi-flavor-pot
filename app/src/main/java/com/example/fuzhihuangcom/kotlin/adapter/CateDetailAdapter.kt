package com.example.fuzhihuangcom.kotlin.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.fuzhihuangcom.kotlin.R
import com.example.fuzhihuangcom.kotlin.service.bean.cate.CategoryInfo

/**
 * Created by fzh on 2018/1/23.
 */
class CateDetailAdapter : BaseQuickAdapter<CategoryInfo.ResultBean.ChildsBeanX.ChildsBean, BaseViewHolder>(R.layout.item_cate_detail) {

    override fun convert(helper: BaseViewHolder, item: CategoryInfo.ResultBean.ChildsBeanX.ChildsBean) {
        helper.setText(R.id.tv_cate_detail, item.categoryInfo.name)
    }
}