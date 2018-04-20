package com.kotlin.utils.imageutils

import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.kotlin.utils.BitmapUtils
import java.security.MessageDigest

/**
 * Created by fzh on 2018/1/23.
 */
class SimpleBitmapTransform : BitmapTransformation {

    private var bitmapTransformationConfig: BitmapTransformationConfig? = null

    constructor(bitmapTransformationConfig: BitmapTransformationConfig) {
        this.bitmapTransformationConfig = bitmapTransformationConfig
    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap? {
        if (bitmapTransformationConfig == null) {
            return toTransform
        } else {
            return BitmapUtils.shapeCrop(pool, toTransform, bitmapTransformationConfig!!)
        }
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest?) {

    }
}