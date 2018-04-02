package com.example.fuzhihuangcom.kotlin.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fuzhihuangcom.kotlin.R
import com.example.fuzhihuangcom.kotlin.activity.ImageActivity
import com.example.fuzhihuangcom.kotlin.adapter.GirlAdapter
import com.example.fuzhihuangcom.kotlin.common.Constants.Companion.ERROR_NO_CONTENT
import com.example.fuzhihuangcom.kotlin.common.Constants.Companion.ERROR_TIP
import com.example.fuzhihuangcom.kotlin.service.bean.BaiduGirlInfo
import com.example.fuzhihuangcom.kotlin.service.presenter.GirlPresenter
import com.example.fuzhihuangcom.kotlin.service.view.GirlView
import kotlinx.android.synthetic.main.fragment_girl.*

/**
 * Created by fzh on 2018/1/22.
 */
class GirlFragment : BaseLazyFragment() {

    var num = 0
    private var girlInfoList: MutableList<BaiduGirlInfo.DataBean> = ArrayList()
    private lateinit var girlAdapter: GirlAdapter

    private lateinit var girlPresenter: GirlPresenter
    private var girlView: GirlView = object : GirlView {
        override fun onSuccess(girlInfo: BaiduGirlInfo) {
            refreshLayout.finishRefresh()               // 结束刷新
            girlInfoList = girlInfo.data as MutableList<BaiduGirlInfo.DataBean>
            if (girlInfoList.size == 11) {
                girlInfoList.removeAt(10)
            }
            if (num == 0 && TextUtils.isEmpty(girlInfo.toString())) {
                showToast(ERROR_NO_CONTENT)
            } else {
                if (num == 0) {
                    girlAdapter.setNewData(girlInfoList)
                } else {
                    girlAdapter.addData(girlInfoList)
                }
            }
            if (girlInfoList.size > 0 && girlInfoList.size == 10) {
                girlAdapter.loadMoreComplete()
            } else {
                girlAdapter.loadMoreEnd()
            }
            num++
        }

        override fun onError(result: String) {
            refreshLayout.finishRefresh(false)  // 结束刷新（刷新失败）
            showToast(ERROR_TIP)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         View.inflate(context, R.layout.fragment_girl, null)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        bindRequest()
        initView()
    }

    private fun bindRequest() {
        girlPresenter = GirlPresenter(context)
        girlPresenter.onCreate()
        girlPresenter.attachView(girlView)
    }

    private fun initView() {
        recyclerview.layoutManager = GridLayoutManager(context, 2)
        girlAdapter = GirlAdapter()
        recyclerview.adapter = girlAdapter
        // 上拉加载
        girlAdapter.setOnLoadMoreListener({
            if (girlAdapter.data.size > 0) {
                girlPresenter.getGirlInfo(num * 10)
            }
        }, recyclerview)
        // 下拉刷新
        refreshLayout.setOnRefreshListener { girlPresenter.getGirlInfo(0) }
        // adapter条目点击
        girlAdapter.setOnItemClickListener { adapter, _, position ->
            val data: BaiduGirlInfo.DataBean = adapter.data[position] as BaiduGirlInfo.DataBean
            val url: String = data.image_url
            ImageActivity.start(context, url)
        }
    }

    override fun lazyLoadData() {
        girlPresenter.getGirlInfo(num * 10)
    }

    override fun onDestroy() {
        super.onDestroy()
        girlPresenter.onStop()
    }
}