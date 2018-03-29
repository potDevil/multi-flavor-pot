package com.example.fuzhihuangcom.kotlin.utils.imageutils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.example.fuzhihuangcom.kotlin.R

/**
 * Created by fzh on 2018/1/23.
 */
class GlideAvaImpl : GlideAva {

    private lateinit var mContext: Context
    private val errorResId: Int = R.drawable.icon_white
    private val placeholderResId: Int = R.drawable.icon_white
    private lateinit var mOptions: RequestOptions
    private lateinit var mRoundRectOptions: RequestOptions
    private var mCircleOptions: RequestOptions? = null

    override fun initGlide(content: Context) {
        this.mContext = content
        mOptions = RequestOptions()
                .centerCrop()
                .placeholder(placeholderResId)
                .error(errorResId)
                .priority(Priority.HIGH)
    }

    override fun loadImage(url: String?, imageView: ImageView?) {
        Glide.with(mContext).load(url).apply(mOptions).into(imageView)
    }

    override fun loadCircleImage(url: String?, imageView: ImageView?) {
        if (mCircleOptions == null) {
            var bitmapTransformationConfig: BitmapTransformationConfig = BitmapTransformationConfig()
                    .setBorderShape(BitmapTransformationConfig.BorderShape.CIRCLE_SHAPE)
            var simpleBitmapTransform: SimpleBitmapTransform = SimpleBitmapTransform(bitmapTransformationConfig)
            mCircleOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(placeholderResId)
                    .error(errorResId)
                    .priority(Priority.HIGH)
                    .transform(simpleBitmapTransform)
        }
        Glide.with(mContext).load(url).apply(mCircleOptions!!).into(imageView)
    }

    override fun loadRoundRectImage(radian: Int, url: String?, imageView: ImageView?) {
        if(mRoundRectOptions == null) {
            var bitmapTransformationConfig: BitmapTransformationConfig = BitmapTransformationConfig()
                    .setBorderShape(BitmapTransformationConfig.BorderShape.VERTIVAL_ROUND_SHAPE)
                    .setRoundCorner(radian)
            var simpleBitmapTransform: SimpleBitmapTransform = SimpleBitmapTransform(bitmapTransformationConfig)
            mRoundRectOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(placeholderResId)
                    .error(errorResId)
                    .priority(Priority.HIGH)
                    .transform(simpleBitmapTransform)
        }
        Glide.with(mContext).load(url).apply(mRoundRectOptions!!).into(imageView)
    }
}