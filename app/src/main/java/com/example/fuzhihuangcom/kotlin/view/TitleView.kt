package com.example.fuzhihuangcom.kotlin.view

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.fuzhihuangcom.kotlin.R



/**
 * Created by fzh on 2018/1/30.
 */
class TitleView : LinearLayout {

    private var mContext: Context? = null
    private var iv_exit: ImageView? = null
    private var tv_title: TextView? = null

    constructor(context: Context) : this(context, null) {}
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mContext = context
        val view = View.inflate(context, R.layout.title_view, this)
        iv_exit = view.findViewById<ImageView>(R.id.iv_exit)
        tv_title = view.findViewById(R.id.tv_title)

        val onClickListener:OnClickListener = object :OnClickListener{
            override fun onClick(v: View?) {
                when(v!!.id){
                    R.id.iv_exit -> (getContext() as Activity).finish()
                }
            }
        }

        iv_exit!!.setOnClickListener(onClickListener)
    }

    fun setExitIcon(drawable: Drawable) {
        if (iv_exit != null)
            iv_exit!!.setImageDrawable(drawable)
    }

    fun setTextTitle(text :String) {
        if(tv_title != null)
            tv_title!!.setText(text)
    }
}