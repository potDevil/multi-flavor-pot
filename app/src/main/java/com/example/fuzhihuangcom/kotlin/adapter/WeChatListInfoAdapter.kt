package com.example.fuzhihuangcom.kotlin.adapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.fuzhihuangcom.kotlin.R
import com.example.fuzhihuangcom.kotlin.common.Constants.Companion.PICTURE_URL
import com.example.fuzhihuangcom.kotlin.service.bean.wechat.WeChatListInfo
import com.example.fuzhihuangcom.kotlin.utils.imageutils.GlideAvaUtil

/**
 * Created by fzh on 2018/4/2.
 */
class WeChatListInfoAdapter : BaseQuickAdapter<WeChatListInfo.ListBean, BaseViewHolder>(R.layout.item_wechat_list) {
    override fun convert(helper: BaseViewHolder, item: WeChatListInfo.ListBean) {
        GlideAvaUtil.initGlide(mContext)
        if (!TextUtils.isEmpty(item.thumbnails))
            GlideAvaUtil.loadImage(item.thumbnails, helper.getView(R.id.iv_picture))
        else
            GlideAvaUtil.loadImage(PICTURE_URL, helper.getView(R.id.iv_picture))
        helper.setText(R.id.tv_title, item.title)
                .setText(R.id.tv_sub_title, item.subTitle)
                .setText(R.id.tv_pub_time, item.pubTime)
    }
}