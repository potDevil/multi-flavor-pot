package com.example.fuzhihuangcom.kotlin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.example.fuzhihuangcom.kotlin.R
import com.example.fuzhihuangcom.kotlin.adapter.CateCookingAdapter
import com.example.fuzhihuangcom.kotlin.common.Constants.Companion.CATE_DETAIL
import com.example.fuzhihuangcom.kotlin.common.Constants.Companion.PICTURE_URL
import com.example.fuzhihuangcom.kotlin.service.bean.cate.CateDetailListInfo
import com.example.fuzhihuangcom.kotlin.service.bean.cate.CateRecipeMethod
import com.example.fuzhihuangcom.kotlin.utils.imageutils.GlideAvaUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_cate_detail.*
import java.util.*

/**
 * Created by fzh on 2018/4/2.
 */
class CateDetailActivity : BaseActivity() {

    private var data: CateDetailListInfo.ListBean? = null
    private var cateCookingAdapter: CateCookingAdapter? = null
    private var cateRecipeMethos: ArrayList<CateRecipeMethod>? = null

    companion object {
        fun start(context: Context, data: CateDetailListInfo.ListBean) {
            val intent = Intent(context, CateDetailActivity().javaClass)
            intent.putExtra(CATE_DETAIL, data)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cate_detail)
        getDataForActivity()
        initView()
        initRecycleView()
        setData()
    }

    private fun getDataForActivity() {
        data = intent.getSerializableExtra(CATE_DETAIL) as CateDetailListInfo.ListBean?
    }

    private fun initView() {
        GlideAvaUtil.initGlide(this)
        if (!TextUtils.isEmpty(data?.thumbnail))
            GlideAvaUtil.loadImage(data?.recipe?.img, iv_cate_detail)
        else
            GlideAvaUtil.loadImage(PICTURE_URL, iv_cate_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener({ finish() })
        supportActionBar?.title = data?.name
        /// 标题颜色
    }

    private fun initRecycleView() {
        cateCookingAdapter = CateCookingAdapter()
        rv_cate_detail.layoutManager = LinearLayoutManager(this)
        rv_cate_detail.adapter = cateCookingAdapter
        cateCookingAdapter?.addHeaderView(getHeaderView())
    }

    private fun getHeaderView(): View {
        val view = View.inflate(this, R.layout.header_cate_cooking_adapter, null)
        val tv_intro_content: TextView = view.findViewById(R.id.tv_intro_content)
        val tv_materials_content: TextView = view.findViewById(R.id.tv_materials_content)
        tv_intro_content.text = data?.recipe?.sumary
        val split = data?.recipe?.ingredients?.replace("[", "")?.replace("]", "")?.replace("\"", "")?.trim()?.split(",")
        var materials = ""
        split?.size?.let {
            for (i in 0 until it) {
                materials += if (i != split.size - 1)
                    split[i] + "\n"
                else
                    split[i]
            }
        }
        tv_materials_content.text = materials
        return view
    }

    private fun setData() {
        var str = data?.recipe?.method
        if (null == str || TextUtils.isEmpty(str)) {
            cateRecipeMethos = ArrayList()
        } else {
            str = str.replace("\\", "")
            cateRecipeMethos = Gson().fromJson(
                    str, object : TypeToken<ArrayList<CateRecipeMethod>>() {
            }.type)
        }
        if (cateRecipeMethos != null)
            cateCookingAdapter?.setNewData(cateRecipeMethos)
    }
}