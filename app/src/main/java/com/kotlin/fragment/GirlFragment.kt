package com.kotlin.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.R
import com.kotlin.activity.ImageActivity
import com.kotlin.adapter.GirlAdapter
import com.kotlin.common.ERROR_NO_CONTENT
import com.kotlin.common.ERROR_TIP
import com.kotlin.service.bean.BaiduGirlInfo
import com.kotlin.service.presenter.GirlPresenter
import com.kotlin.service.view.GirlView
import kotlinx.android.synthetic.main.fragment_girl.*

/**
 * Created by fzh on 2018/1/22.
 */
@Deprecated("grilFragment")
class GirlFragment : BaseLazyFragment<GirlPresenter, GirlView>() {

    var num = 0
    private var girlInfoList: MutableList<BaiduGirlInfo.DataBean> = ArrayList()
    private lateinit var girlAdapter: GirlAdapter

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

    override fun initRequest() {
        basePresenter = GirlPresenter(context)
        baseView = girlView
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(context, R.layout.fragment_girl, null)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        recyclerview.layoutManager = GridLayoutManager(context, 2)
        girlAdapter = GirlAdapter()
        recyclerview.adapter = girlAdapter
        // 上拉加载
        girlAdapter.setOnLoadMoreListener({
            if (girlAdapter.data.size > 0) {
                basePresenter?.getGirlInfo(num * 10)
            }
        }, recyclerview)
        // 下拉刷新
        refreshLayout.setOnRefreshListener { basePresenter?.getGirlInfo(0) }
        // adapter条目点击
        girlAdapter.setOnItemClickListener { adapter, _, position ->
            val data = adapter.data[position] as BaiduGirlInfo.DataBean
            val url = data.image_url
            ImageActivity.start(context, url)
        }
    }

    override fun lazyLoadData() {
        basePresenter?.getGirlInfo(num * 10)
    }
}