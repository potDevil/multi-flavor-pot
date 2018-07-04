package com.kotlin.utils.imageutils

import android.graphics.Color
import android.graphics.Paint
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import com.kotlin.PotApp
import com.kotlin.utils.AndroidUtils

/**
 * Created by fzh on 2018/1/23.
 */
class BitmapTransformationConfig {

    private lateinit var borderShape: BorderShape
    var borderMargin: Int = 0
    @ColorInt
    var borderColor: Int = Color.WHITE
    @ColorInt
    var borderMarginColor: Int = Color.WHITE
    @ColorInt
    var bgColor = Color.WHITE
    private var borderWidth: Int = 0
    private var borderPaint: Paint? = null
    var hasBorder: Boolean = false
    var iconGravitys: IconGravitys? = null
    var roundCorner: Int = 0

    fun setBgColor(bgColor: Int): BitmapTransformationConfig {
        this.bgColor = bgColor
        return this
    }

    fun setBorderMarginColor(borderMarginColor: Int): BitmapTransformationConfig {
        this.borderMarginColor = borderMarginColor
        return this
    }

    fun setRoundCorner(roundCorner: Int): BitmapTransformationConfig {
        this.roundCorner = roundCorner
        return this
    }

    fun setIconGravitys(iconGravitys: IconGravitys): BitmapTransformationConfig {
        this.iconGravitys = iconGravitys
        return this
    }

    fun setBorderPaint(borderPaint: Paint): BitmapTransformationConfig {
        this.borderPaint = borderPaint
        return this
    }

    fun isHasBorder(): Boolean {
        return hasBorder
    }

    fun setHasBorder(hasBorder: Boolean): BitmapTransformationConfig {
        this.hasBorder = hasBorder
        return this
    }

    fun getBorderShape(): BorderShape {
        return borderShape
    }

    fun setBorderShape(borderShape: BorderShape): BitmapTransformationConfig {
        this.borderShape = borderShape
        return this
    }

    fun setBorderMargin(borderMargin: Int): BitmapTransformationConfig {
        this.borderMargin = borderMargin
        return this
    }

    fun setBorderColor(borderColor: Int): BitmapTransformationConfig {
        this.borderColor = borderColor
        return this
    }

    fun getBorderWidth(): Int {
        return AndroidUtils.dip2px(PotApp.instance, borderWidth.toFloat())
    }

    fun getBorderPaint(): Paint {
        if (borderPaint == null) {
            borderPaint = Paint()
            borderPaint?.isAntiAlias = true
            borderPaint?.style = Paint.Style.STROKE
        }
        borderPaint?.strokeWidth = getBorderWidth().toFloat()
        borderPaint?.color = borderColor
        return borderPaint!!
    }

    fun setBorderWidth(borderWidth: Int): BitmapTransformationConfig {
        this.borderWidth = borderWidth
        return this
    }

    enum class BorderShape(val value: Int) {
        CIRCLE_SHAPE(1),
        ROUND_SHAPE(2),
        RECT_SHAPE(0),
        VERTIVAL_ROUND_SHAPE(3)
    }

    class IconGravitys {
        @DrawableRes
        private var leftTopDrawable: Int = 0
        @DrawableRes
        private var leftBottomDrawable: Int = 0
        @DrawableRes
        private var rightTopDrawable: Int = 0
        @DrawableRes
        private var rightBottomDrawable: Int = 0
        private var leftTopMargin: Int = 0
        private var leftBottomMargin: Int = 0
        private var rightTopMargin: Int = 0
        private var rightBottomMargin: Int = 0

        fun getLeftTopDrawable(): Int {
            return leftTopDrawable
        }

        fun setLeftTopDrawable(leftTopDrawable: Int): IconGravitys {
            this.leftTopDrawable = leftTopDrawable
            return this
        }

        fun getLeftBottomDrawable(): Int {
            return leftBottomDrawable
        }

        fun setLeftBottomDrawable(leftBottomDrawable: Int): IconGravitys {
            this.leftBottomDrawable = leftBottomDrawable
            return this
        }

        fun getRightTopDrawable(): Int {
            return rightTopDrawable
        }

        fun setRightTopDrawable(rightTopDrawable: Int): IconGravitys {
            this.rightTopDrawable = rightTopDrawable
            return this
        }

        fun getRightBottomDrawable(): Int {
            return rightBottomDrawable
        }

        fun setRightBottomDrawable(rightBottomDrawable: Int): IconGravitys {
            this.rightBottomDrawable = rightBottomDrawable
            return this
        }

        fun getLeftTopMargin(): Int {
            return leftTopMargin
        }

        fun setLeftTopMargin(leftTopMargin: Int): IconGravitys {
            this.leftTopMargin = leftTopMargin
            return this
        }

        fun getLeftBottomMargin(): Int {
            return leftBottomMargin
        }

        fun setLeftBottomMargin(leftBottomMargin: Int): IconGravitys {
            this.leftBottomMargin = leftBottomMargin
            return this
        }

        fun getRightTopMargin(): Int {
            return rightTopMargin
        }

        fun setRightTopMargin(rightTopMargin: Int): IconGravitys {
            this.rightTopMargin = rightTopMargin
            return this
        }

        fun getRightBottomMargin(): Int {
            return rightBottomMargin
        }

        fun setRightBottomMargin(rightBottomMargin: Int): IconGravitys {
            this.rightBottomMargin = rightBottomMargin
            return this
        }
    }
}