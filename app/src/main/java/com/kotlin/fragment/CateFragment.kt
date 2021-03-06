package com.kotlin.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.R
import com.kotlin.activity.CateListActivity
import com.kotlin.adapter.CateCategoryAdapter
import com.kotlin.adapter.CateDetailAdapter
import com.kotlin.common.ERROR_TIP
import com.kotlin.common.PICTURE_URL
import com.kotlin.service.bean.cate.CategoryInfo
import com.kotlin.service.presenter.CateCategoryPresenter
import com.kotlin.service.view.CateCategoryView
import com.kotlin.utils.imageutils.GlideAvaUtil
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_cate.*

/**
 * Created by fzh on 2018/1/22.
 */

//TODO 后期需要增加的两个功能 1、search cate 2、save cate
class CateFragment : BaseLazyFragment<CateCategoryPresenter, CateCategoryView>() {

//    override fun initPresenter(): CateCategoryPresenter {
//        return CateCategoryPresenter(context)
//    }

    private var cateCategoryAdapter: CateCategoryAdapter? = null
    private var cateDetailAdapter: CateDetailAdapter? = null
    private var categoryInfos: CategoryInfo? = null
    private var cateCategory = ArrayList<CategoryInfo.ChildsBeanX>()

    private var cateCategoryView: CateCategoryView = object : CateCategoryView {
        override fun onLoadCateDataSuccess(t: CategoryInfo?) {
            refreshLayout.finishRefresh()
            categoryInfos = t
            cateCategory.clear()
            // 菜系分类
            for (i in 0 until (t?.childs?.size ?: 0)) {
                t?.childs?.get(i)?.let { it -> cateCategory.add(it) }
            }
            cateCategory[0].categoryInfo.select = true
            cateCategoryAdapter?.setNewData(cateCategory)

            val cateDetailInfos = ArrayList<CategoryInfo.ChildsBeanX.ChildsBean>()
            for (it in 0 until (categoryInfos?.childs?.get(0)?.childs?.size ?: 0)) {
                categoryInfos?.childs?.get(0)?.childs?.get(it)?.let { it1 -> cateDetailInfos.add(it1) }
            }
            cateDetailAdapter?.setNewData(cateDetailInfos)
        }

        override fun onLoadCateDataError(s: String?) {
            onLoadError()
        }
    }

    override fun initRequest() {
        basePresenter = CateCategoryPresenter(context)
        baseView = cateCategoryView
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(context, R.layout.fragment_cate, null)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Hawk.init(context).build()
        initView()
        initRecyclerView()
        itemOnclickListener()
    }

    private fun initView() {
        val catePicture = PICTURE_URL
        GlideAvaUtil.initGlide(context)
        GlideAvaUtil.loadImage(catePicture, iv_bg)
    }

    private fun initRecyclerView() {
        cateCategoryAdapter = CateCategoryAdapter()
        rv_list_category.layoutManager = LinearLayoutManager(context)
        rv_list_category.adapter = cateCategoryAdapter
        cateDetailAdapter = CateDetailAdapter()
        rv_list_content.layoutManager = GridLayoutManager(context, 3)
        rv_list_content.adapter = cateDetailAdapter
        // 下拉刷新
        refreshLayout.setOnRefreshListener {  basePresenter?.getCateCategory() }
    }

    private fun itemOnclickListener() {
        cateCategoryAdapter?.setOnItemClickListener { _, _, position ->
            for (i in 0 until cateCategory.size) {
                cateCategory[i].categoryInfo.select = i == position
            }
            cateCategoryAdapter?.notifyDataSetChanged()
            val cateDetailInfos = ArrayList<CategoryInfo.ChildsBeanX.ChildsBean>()
            for (i in 0 until (categoryInfos?.childs?.get(position)?.childs?.size ?: 0)) {
                categoryInfos?.childs?.get(position)?.childs?.get(i)?.let { it -> cateDetailInfos.add(it) }
            }
            cateDetailAdapter?.setNewData(cateDetailInfos)
        }

        cateDetailAdapter?.setOnItemClickListener { adapter, _, position ->
            val allChildCategorys = adapter.getItem(position) as CategoryInfo.ChildsBeanX.ChildsBean
            CateListActivity.start(context, allChildCategorys.categoryInfo.ctgId, allChildCategorys.categoryInfo.name)
        }
    }

    private fun onLoadError() {
        refreshLayout.finishRefresh(false)  // 结束刷新（刷新失败）
        showToast(ERROR_TIP)
    }

    override fun lazyLoadData() {
        basePresenter?.getCateCategory()
    }
}