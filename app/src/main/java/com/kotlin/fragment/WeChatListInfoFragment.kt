package com.kotlin.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.R
import com.kotlin.activity.X5WebViewActivity
import com.kotlin.adapter.WeChatListInfoAdapter
import com.kotlin.common.ERROR_NO_CONTENT
import com.kotlin.common.ERROR_TIP
import com.kotlin.common.WECHAT_CID
import com.kotlin.service.bean.wechat.WeChatListInfo
import com.kotlin.service.presenter.WeChatListPresenter
import com.kotlin.service.view.WeChatListView
import kotlinx.android.synthetic.main.activity_choice_city.*

/**
 * Created by fzh on 2018/4/9.
 */
class WeChatListInfoFragment : BaseLazyFragment<WeChatListPresenter, WeChatListView>() {

    private var rv_wechat_info: RecyclerView? = null
    private var weChatListInfoAdapter: WeChatListInfoAdapter? = null
    private var cid = ""
    private var page = 1
    private var size = 20

    private var weChatListView = object : WeChatListView {
        override fun onLoadWeChatListSuccess(t: List<WeChatListInfo.ListBean>?) {
            refreshLayout.finishRefresh()
            if (page == 1 && t?.size == 0) {
                showToast(ERROR_NO_CONTENT)
            } else {
                if (page == 1) {
                    weChatListInfoAdapter?.setNewData(t)
                } else {
                    t?.let { it -> weChatListInfoAdapter?.addData(it) }
                }
            }
            if (t?.size ?: 0 > 0 && t?.size ?: 0 == 20) {
                weChatListInfoAdapter?.loadMoreComplete()
            } else {
                weChatListInfoAdapter?.loadMoreEnd()
            }
            page++
        }

        override fun onLoadWeChatListError(s: String?) {
            refreshLayout.finishRefresh(false)
            showToast(ERROR_TIP)
        }

    }

    override fun initRequest() {
        basePresenter = WeChatListPresenter(context)
        baseView = weChatListView
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(context, R.layout.fragment_wechat_info_list, null)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        page = 1
        initView(view)
        refreshView()
    }

    private fun initView(view: View?) {
        weChatListInfoAdapter = WeChatListInfoAdapter()
        rv_wechat_info = view?.findViewById(R.id.rv_wechat_info)
        rv_wechat_info?.layoutManager = LinearLayoutManager(context)
        rv_wechat_info?.adapter = weChatListInfoAdapter
        cid = arguments.get(WECHAT_CID) as String
        basePresenter?.getWeChatList(cid, page, size)

        weChatListInfoAdapter?.setOnLoadMoreListener({
            if (weChatListInfoAdapter?.data?.size ?: 0 > 0) {
                basePresenter?.getWeChatList(cid, page, size)
            }
        }, rv_wechat_info)

        weChatListInfoAdapter?.setOnItemClickListener { adapter, _, position ->
            val data = adapter.data[position] as WeChatListInfo.ListBean
            val title = data.subTitle
            val url = data.sourceUrl
            X5WebViewActivity.start(context, title, url)
        }
    }

    private fun refreshView() {
        refreshLayout.setOnRefreshListener { basePresenter?.getWeChatList(cid, 1, size) }
    }

    override fun lazyLoadData() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (hidden) {
            basePresenter?.onStop()
        }
    }
}