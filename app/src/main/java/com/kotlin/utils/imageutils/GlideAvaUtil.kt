package com.kotlin.utils.imageutils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView

/**
 * Created by fzh on 2018/1/24.
 */
object GlideAvaUtil : GlideAva {

    @SuppressLint("StaticFieldLeak")
    private var mGlideAvaImp: GlideAvaImpl = GlideAvaImpl()

    override fun initGlide(content: Context) {
        mGlideAvaImp.initGlide(content)
    }

    override fun loadImage(url: String?, imageView: ImageView?) {
        mGlideAvaImp.loadImage(url, imageView)
    }

    override fun loadCircleImage(url: String?, imageView: ImageView?) {
        mGlideAvaImp.loadCircleImage(url, imageView)
    }

    override fun loadRoundRectImage(radian: Int, url: String?, imageView: ImageView?) {
        mGlideAvaImp.loadRoundRectImage(radian, url, imageView)
    }
}