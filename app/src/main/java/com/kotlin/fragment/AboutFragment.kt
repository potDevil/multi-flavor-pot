package com.kotlin.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.R
import kotlinx.android.synthetic.main.fragment_about.*

/**
 * Created by fzh on 2018/1/22.
 */
class AboutFragment : BaseLazyFragment(), View.OnClickListener {

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_look_code -> startHtml(getString(R.string.about_html_git_location))
            R.id.btn_share_code -> {
            }
            R.id.btn_look_blog -> startHtml(getString(R.string.about_html_look_blog))
            R.id.btn_share_app -> {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.fragment_about, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        btn_look_code.setOnClickListener(this)
        btn_share_code.setOnClickListener(this)
        btn_look_blog.setOnClickListener(this)
        btn_share_app.setOnClickListener(this)
    }

    private fun startHtml(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = uri
        startActivity(intent)
    }

    override fun lazyLoadData() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (hidden) {

        }
    }
}