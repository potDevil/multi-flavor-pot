package com.example.fuzhihuangcom.kotlin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.example.fuzhihuangcom.kotlin.R
import com.example.fuzhihuangcom.kotlin.common.Constants.Companion.IMAGE_URL
import com.example.fuzhihuangcom.kotlin.utils.imageutils.GlideAvaUtil

/**
 * Created by fzh on 2018/1/25.
 * 全图图片类
 */
class ImageActivity : BaseActivity() {

    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        var iv_activity : ImageView = findViewById(R.id.iv_activity)
        url = intent.getStringExtra(IMAGE_URL)
        GlideAvaUtil.loadImage(url, iv_activity)
        iv_activity.setOnClickListener {
            finish()
        }
    }

    companion object {
        fun start(context: Context, url: String) {
            var intent = Intent(context, ImageActivity::class.java)
            intent.putExtra(IMAGE_URL, url)
            context.startActivity(intent)
        }
    }
}