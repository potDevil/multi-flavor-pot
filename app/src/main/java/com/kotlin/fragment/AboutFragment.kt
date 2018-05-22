package com.kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.R

/**
 * Created by fzh on 2018/1/22.
 */
class AboutFragment : BaseLazyFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.fragment_about, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {

    }

    override fun lazyLoadData() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (hidden) {

        }
    }
}