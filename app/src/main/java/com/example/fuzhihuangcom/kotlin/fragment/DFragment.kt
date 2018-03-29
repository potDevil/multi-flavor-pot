package com.example.fuzhihuangcom.kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fuzhihuangcom.kotlin.R

/**
 * Created by fzh on 2018/1/22.
 */
class DFragment : BaseLazyFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.fragment_d, null)
    }

    override fun lazyLoadData() {

    }
}