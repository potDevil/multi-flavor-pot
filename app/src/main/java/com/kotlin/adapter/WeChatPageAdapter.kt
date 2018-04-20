package com.kotlin.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kotlin.common.WECHAT_CID
import com.kotlin.fragment.WeChatListInfoFragment
import com.kotlin.service.bean.wechat.WeChatCategoryInfo

/**
 * Created by fzh on 2018/4/9.
 */
class WeChatPageAdapter(fm: FragmentManager, val cidInfos: ArrayList<WeChatCategoryInfo>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val fragment = WeChatListInfoFragment()
        // 利用bundle传值
        val bundle = Bundle()
        bundle.putString(WECHAT_CID, cidInfos[position].cid)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return cidInfos.size
    }
}