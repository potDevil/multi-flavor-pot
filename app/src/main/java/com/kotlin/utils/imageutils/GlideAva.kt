package com.kotlin.utils.imageutils

import android.content.Context
import android.widget.ImageView

/**
 * Created by fzh on 2018/1/23.
 */
interface GlideAva {
    fun initGlide(content: Context)

    fun loadImage(url: String?, imageView: ImageView?)

    fun loadCircleImage(url: String?, imageView: ImageView?)

    fun loadRoundRectImage(radian: Int, url: String?, imageView: ImageView?)
}