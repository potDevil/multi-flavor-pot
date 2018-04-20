package com.kotlin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.kotlin.R
import com.kotlin.common.IMAGE_URL
import com.kotlin.utils.imageutils.GlideAvaUtil

/**
 * Created by fzh on 2018/1/25.
 * 全图图片类
 */
class ImageActivity : BaseActivity() {

    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val iv_activity = findViewById(R.id.iv_activity) as ImageView
        url = intent.getStringExtra(IMAGE_URL)
        GlideAvaUtil.loadImage(url, iv_activity)
        iv_activity.setOnClickListener {
            finish()
        }
    }

    companion object {
        fun start(context: Context, url: String) {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putExtra(IMAGE_URL, url)
            context.startActivity(intent)
        }
    }
}