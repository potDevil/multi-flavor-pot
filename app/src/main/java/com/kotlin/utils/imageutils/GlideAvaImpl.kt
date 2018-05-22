package com.kotlin.utils.imageutils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.kotlin.R

/**
 * Created by fzh on 2018/1/23.
 */
class GlideAvaImpl : GlideAva {

    private lateinit var mContext: Context
    private val errorResId: Int = R.drawable.icon_white
    private val placeholderResId: Int = R.drawable.icon_white
    private lateinit var mOptions: RequestOptions
    private var mCircleOptions: RequestOptions? = null
    private var mRoundRectOptions: RequestOptions? = null

    override fun initGlide(content: Context) {
        this.mContext = content
        mOptions = RequestOptions()
                .centerCrop()
//                .skipMemoryCache(false)     // 禁止缓存
                .placeholder(placeholderResId)
                .error(errorResId)
                .priority(Priority.HIGH)
    }

    override fun loadImage(url: String?, imageView: ImageView?) {
        Glide.with(mContext).load(url).apply(mOptions).into(imageView)
    }

    override fun loadCircleImage(url: String?, imageView: ImageView?) {
        if (mCircleOptions == null) {
            val bitmapTransformationConfig: BitmapTransformationConfig = BitmapTransformationConfig()
                    .setBorderShape(BitmapTransformationConfig.BorderShape.CIRCLE_SHAPE)
            val simpleBitmapTransform = SimpleBitmapTransform(bitmapTransformationConfig)
            mCircleOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(placeholderResId)
                    .error(errorResId)
                    .priority(Priority.HIGH)
                    .transform(simpleBitmapTransform)
        }
        mCircleOptions?.let { it -> Glide.with(mContext).load(url).apply(it).into(imageView) }
    }

    override fun loadRoundRectImage(radian: Int, url: String?, imageView: ImageView?) {
        if (mRoundRectOptions == null) {
            val bitmapTransformationConfig: BitmapTransformationConfig = BitmapTransformationConfig()
                    .setBorderShape(BitmapTransformationConfig.BorderShape.VERTIVAL_ROUND_SHAPE)
                    .setRoundCorner(radian)
            val simpleBitmapTransform = SimpleBitmapTransform(bitmapTransformationConfig)
            mRoundRectOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(placeholderResId)
                    .error(errorResId)
                    .priority(Priority.HIGH)
                    .transform(simpleBitmapTransform)
        }
        mRoundRectOptions?.let { Glide.with(mContext).load(url).apply(it).into(imageView) }
    }
}