package com.example.fuzhihuangcom.kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fuzhihuangcom.kotlin.R
import com.example.fuzhihuangcom.kotlin.utils.imageutils.GlideAvaUtil
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_cate.*

/**
 * Created by fzh on 2018/1/22.
 */
class CateFragment : BaseLazyFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(context, R.layout.fragment_cate, null)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Hawk.init(context).build()
        initView()
        initRecyclerView()
    }

    private fun initView() {
        val catePicture = "https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1522292760&di=d2a4c3c5e98bf134ffa3744405062cfd&src=http://img.zcool.cn/community/01d3c0584e960ca8012060c803dd37.jpg@1280w_1l_2o_100sh.jpg"
        GlideAvaUtil.initGlide(context)
        GlideAvaUtil.loadImage(catePicture, iv_bg)
    }

    private fun initRecyclerView() {
//        rv_list_content.layoutManager = GridLayoutManager(context, 3)

    }

    override fun lazyLoadData() {

    }
}