package com.example.fuzhihuangcom.kotlin.utils

import android.graphics.*
import android.support.annotation.DrawableRes
import android.view.Gravity
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.example.fuzhihuangcom.kotlin.PotApp
import com.example.fuzhihuangcom.kotlin.utils.imageutils.BitmapTransformationConfig

/**
 * Created by fzh on 2018/1/23.
 */

object BitmapUtils {
    val LEFT = 1
    val TOP = 1 shl 1
    val RIGHT = 1 shl 2
    val BOTTOM = 1 shl 3

    fun addMarkToBitmap(source: Bitmap, target: Bitmap, gravity: Int): Bitmap {
        var source = source
        //创建一个和原图同样大小的位图

        if (!source.isMutable) {
            source = source.copy(Bitmap.Config.ARGB_8888, true)
        }
        val canvas = Canvas(source)
        val paint = Paint()
        val left = (if (gravity and Gravity.RIGHT != 0) source.width - target.width else 0).toFloat()
        val top = (if (gravity and Gravity.BOTTOM != 0) source.height - target.height else 0).toFloat()
        canvas.drawBitmap(target, left, top, paint)//插入图标
        canvas.save(Canvas.ALL_SAVE_FLAG)
        //存储新合成的图片
        canvas.restore()
        return source
    }

    @JvmOverloads
    fun addMarkToBitmap(source: Bitmap, @DrawableRes drawable: Int, gravity: Int, margin: Int = 0): Bitmap {
        var source = source
        var margin = margin
        margin = AndroidUtils.dip2px(PotApp.instance(), margin.toFloat())
        val target = BitmapFactory.decodeResource(PotApp.instance().resources, drawable)
        //创建一个和原图同样大小的位图
        //        Bitmap newBitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.RGB_565);
        if (!source.isMutable) {
            source = source.copy(Bitmap.Config.ARGB_8888, true)
        }
        val canvas = Canvas(source)
        val paint = Paint()
        val left = (if (gravity and RIGHT != 0) source.width - target.width - margin else margin).toFloat()
        val top = (if (gravity and BOTTOM != 0) source.height - target.height - margin else margin).toFloat()
        canvas.drawBitmap(target, left, top, paint)//插入图标
        canvas.save(Canvas.ALL_SAVE_FLAG)
        //存储新合成的图片
        canvas.restore()
        return source
    }

    fun setPaintHorizontalGradient(paint: Paint, fromColor: Int, toColor: Int, viewWidth: Float): Paint {
        val linearGradient = LinearGradient(0f, 0f, viewWidth, 0f,
                intArrayOf(fromColor, toColor), null, Shader.TileMode.REPEAT)
        paint.shader = linearGradient
        return paint
    }

    fun drawBorder(source: Bitmap, config: BitmapTransformationConfig): Bitmap {
        val width = source.width + config.getBorderWidth() + config.borderMargin
        val height = source.height + config.getBorderWidth() + config.borderMargin
        val size = Math.min(width, height)


        val result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        //        Paint paint = new Paint();
        //        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader
        //                .TileMode.CLAMP));
        //        paint.setAntiAlias(true);
        //        canvas.drawBitmap(source, config.getBorderMargin() + config.getBorderWidth(), config.getBorderMargin() + config.getBorderWidth(), paint);

        if (config.getBorderShape() === BitmapTransformationConfig.BorderShape.CIRCLE_SHAPE) {
            val radius = (size / 2).toFloat()
            if (config.isHasBorder()) {
                val borderPaint = config.getBorderPaint()
                canvas.drawCircle(radius, radius, radius - config.getBorderWidth() / 2, borderPaint)
                borderPaint.color = config.borderMarginColor
                borderPaint.strokeWidth = config.borderMargin.toFloat()
                canvas.drawCircle(radius, radius, radius - config.getBorderWidth() -
                        config.borderMargin / 2, borderPaint)
            }
        } else if (config.getBorderShape() === BitmapTransformationConfig.BorderShape.ROUND_SHAPE) {
            val radius = config.roundCorner
            if (config.isHasBorder()) {
                val borderPaint = config.getBorderPaint()
                var borderWidth = config.getBorderWidth()
                canvas.drawRoundRect(RectF(borderWidth.toFloat() / 2, borderWidth.toFloat() / 2, width - borderWidth.toFloat() / 2, (height - borderWidth).toFloat() / 2),
                        radius.toFloat(), radius.toFloat(), borderPaint)
                borderPaint.color = config.borderMarginColor
                borderPaint.strokeWidth = config.borderMargin.toFloat()
                borderWidth += config.borderMargin / 2
                canvas.drawRoundRect(RectF(borderWidth.toFloat(), borderWidth.toFloat(), width - borderWidth.toFloat(), height - borderWidth.toFloat()),
                        radius.toFloat(), radius.toFloat(), borderPaint)
            }
        } else {
            if (config.isHasBorder()) {
                val borderPaint = config.getBorderPaint()
                var borderWidth = config.getBorderWidth()
                canvas.drawRect(RectF(borderWidth.toFloat() / 2, borderWidth.toFloat() / 2, width - borderWidth.toFloat() / 2, (height - borderWidth).toFloat() / 2), borderPaint)
                borderPaint.color = config.borderMarginColor
                borderPaint.strokeWidth = config.borderMargin.toFloat()
                borderWidth += config.borderMargin / 2
                canvas.drawRect(RectF(borderWidth.toFloat(), borderWidth.toFloat(), (width - borderWidth).toFloat(), (height - borderWidth).toFloat()), borderPaint)
            }
        }
        return result
    }

    /**
     * 绘制
     * @param pool
     * @param source
     * @param config
     * @return
     */
    fun shapeCrop(pool: BitmapPool, source: Bitmap?, config: BitmapTransformationConfig): Bitmap? {
        var source: Bitmap? = source ?: return null
        if (config.getBorderShape() === BitmapTransformationConfig.BorderShape.VERTIVAL_ROUND_SHAPE) {
            var width = source!!.width.toFloat()
            val height = source.height.toFloat()
            if (width * 1.2f > height) {
                width = height / 1.2f
            }
            source = Bitmap.createBitmap(source, Math.round((source.width - width) / 2f), 0, Math.round(width), height.toInt())
        }
        val isCircle = config.getBorderShape() === BitmapTransformationConfig.BorderShape.CIRCLE_SHAPE
        val width = source!!.width
        val height = source.height

        var size = Math.min(width, height)
        val left = if (isCircle) (width - size) / 2 else 0
        val top = if (isCircle) (height - size) / 2 else 0
        val lastWidth = if (isCircle) size else width
        val lastHeight = if (isCircle) size else height
        var result: Bitmap? = null
        result = pool.get(lastWidth, lastHeight, Bitmap.Config.ARGB_8888)
        //绘制边框
        if (config.isHasBorder()) {
            result = drawBorder(result, config)
        }

        val canvas = Canvas(result)
        val paint = Paint()
        paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true
        if (config.getBorderShape() === BitmapTransformationConfig.BorderShape.CIRCLE_SHAPE) {
            size = Math.min(result.width, result.height)
            canvas.drawCircle((size / 2).toFloat(), (size / 2).toFloat(), (size / 2 - config.borderMargin - config.getBorderWidth()).toFloat(), paint)
        } else {
            val radius = config.roundCorner
            canvas.drawRoundRect(RectF((config.getBorderWidth() + config.borderMargin).toFloat(), +(config.getBorderWidth() + config.borderMargin).toFloat(),
                    (result.width - config.getBorderWidth() - config.borderMargin).toFloat(), result.height -
                    (config.getBorderWidth() - config.borderMargin).toFloat()), radius.toFloat(), radius.toFloat(), paint)
        }

        //添加drawable
        if (config.iconGravitys != null) {
            if (config.iconGravitys?.getLeftTopDrawable() !== 0) {
                addMarkToBitmap(result, config.iconGravitys?.getLeftTopDrawable() ?: 0, TOP or LEFT,
                        config.iconGravitys?.getLeftTopMargin() ?: 0)
            }

            if (config.iconGravitys?.getLeftBottomDrawable() !== 0) {
                addMarkToBitmap(result, config.iconGravitys?.getLeftBottomDrawable() ?: 0, BOTTOM or LEFT,
                        config.iconGravitys?.getLeftBottomMargin() ?: 0)
            }

            if (config.iconGravitys?.getRightTopDrawable() !== 0) {
                addMarkToBitmap(result, config.iconGravitys?.getRightTopDrawable() ?: 0, TOP or RIGHT,
                        config.iconGravitys?.getRightTopMargin() ?: 0)
            }

            if (config.iconGravitys?.getRightBottomDrawable() !== 0) {
                addMarkToBitmap(result, config.iconGravitys?.getRightBottomDrawable() ?: 0, BOTTOM or RIGHT,
                        config.iconGravitys?.getRightBottomMargin() ?: 0)
            }
        }
        return result
    }
}
