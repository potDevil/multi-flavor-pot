package com.example.fuzhihuangcom.kotlin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.fuzhihuangcom.kotlin.R
import com.example.fuzhihuangcom.kotlin.adapter.CateListAdapter
import com.example.fuzhihuangcom.kotlin.common.CID
import com.example.fuzhihuangcom.kotlin.common.ERROR_NO_CONTENT
import com.example.fuzhihuangcom.kotlin.common.ERROR_TIP
import com.example.fuzhihuangcom.kotlin.common.TITLE
import com.example.fuzhihuangcom.kotlin.service.bean.cate.CateDetailListInfo
import com.example.fuzhihuangcom.kotlin.service.presenter.CateListPresenter
import com.example.fuzhihuangcom.kotlin.service.view.CateListView
import com.example.fuzhihuangcom.kotlin.view.TitleView
import kotlinx.android.synthetic.main.activity_cate_list.*

/**
 * Created by fzh on 2018/3/30.
 */
class CateListActivity : BaseActivity() {

    private lateinit var cateListPresenter: CateListPresenter
    private var cid: String = ""
    private var title: String = ""
    private var page: Int = 1
    private var size: Int = 20
    private var titleText: TitleView? = null
    private var cateListAdapter: CateListAdapter? = null

    companion object {
        fun start(context: Context, id: String, title: String) {
            val intent = Intent(context, CateListActivity().javaClass)
            intent.putExtra(CID, id)
            intent.putExtra(TITLE, title)
            context.startActivity(intent)
        }
    }

    private var cateListView = object : CateListView {
        override fun onLoadCateListSuccess(t: CateDetailListInfo?) {
            refreshLayout.finishRefresh()
            if (page == 1 && t?.list == null) {
                showToast(ERROR_NO_CONTENT)
            } else {
                if (page == 1) {
                    cateListAdapter?.setNewData(t?.list)
                } else {
                    t?.list?.let { it -> cateListAdapter?.addData(it) }
                }
            }
            if (t?.list?.size ?: 0 > 0 && t?.list?.size ?: 0 == 20) {
                cateListAdapter?.loadMoreComplete()
            } else {
                cateListAdapter?.loadMoreEnd()
            }
            page++
        }

        override fun onLoadCateListError(s: String?) {
            refreshLayout.finishRefresh(false)  // 结束刷新（刷新失败）
            showToast(ERROR_TIP)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cate_list)
        cid = intent.getStringExtra(CID)
        title = intent.getStringExtra(TITLE)
        bindRequest()
        refreshView()
        initView()
        initRecycleView()
    }

    private fun bindRequest() {
        cateListPresenter = CateListPresenter(this)
        cateListPresenter.onCreate()
        cateListPresenter.attachView(cateListView)
    }

    private fun refreshView() {
        cateListPresenter.getCateListInfo(cid, page, size)
    }

    private fun initView() {
        titleText = findViewById(R.id.title) as TitleView
        titleText?.setTitleBg(R.color.refresh_blue)
        titleText?.setTextTitle(title)
    }

    private fun initRecycleView() {
        cateListAdapter = CateListAdapter()
        rv_cate_list.layoutManager = LinearLayoutManager(this)
        rv_cate_list.adapter = cateListAdapter

        cateListAdapter?.setOnItemClickListener { adapter, _, position ->
            val data = adapter.getItem(position) as CateDetailListInfo.ListBean
            CateDetailActivity.start(this, data)
        }

        refreshLayout.setOnRefreshListener { refreshView() }

        cateListAdapter?.setOnLoadMoreListener({
            if (cateListAdapter?.data?.size ?: 0 > 0) {
                cateListPresenter.getCateListInfo(cid, page, size)
            }
        }, rv_cate_list)
    }
}