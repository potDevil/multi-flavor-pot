package com.example.fuzhihuangcom.kotlin.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.fuzhihuangcom.kotlin.R

/**
 * Created by fzh on 2018/1/23.
 */
class CateDetailAdapter(data: ArrayList<String>?): BaseQuickAdapter<ArrayList<String>, BaseViewHolder>(R.layout.item_cate_detail) {

    override fun convert(helper: BaseViewHolder, item: ArrayList<String>) {

    }
}