package com.kotlin.view

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.kotlin.R


/**
 * Created by fzh on 2018/1/30.
 */
class TitleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private var iv_exit: ImageView? = null
    private var tv_title: TextView? = null
    private var toolbar_choice_city: RelativeLayout? = null

    init {
        val view = View.inflate(context, R.layout.title_view, this)
        toolbar_choice_city = view.findViewById(R.id.toolbar_choice_city)
        iv_exit = view.findViewById(R.id.iv_exit)
        tv_title = view.findViewById(R.id.tv_title)

        val onClickListener = OnClickListener { v ->
            when (v?.id) {
                R.id.iv_exit -> (getContext() as Activity).finish()
            }
        }

        iv_exit?.setOnClickListener(onClickListener)
    }

    fun setExitIcon(drawable: Drawable) {
        iv_exit?.setImageDrawable(drawable)
    }

    fun setTextTitle(text: String) {
        tv_title?.text = text
    }

    fun setTitleBg(color: Int) {
        toolbar_choice_city?.setBackgroundResource(color)
    }

    fun setTitleLength(length: Int) {
        tv_title?.maxEms = length
    }

    fun setTitleLine(lines: Int) {
        tv_title?.maxLines = lines
    }
}