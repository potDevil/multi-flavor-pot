package com.example.fuzhihuangcom.kotlin.view.dialog

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.widget.TextView
import com.example.fuzhihuangcom.kotlin.R


/**
 * Created by fuzhihuang on 2017/8/29.
 * 个人主页标签弹窗
 */

class AqiDialog(private val mContext: Context) : Dialog(mContext) {

    private var tv_aqi1: TextView? = null
    private var tv_aqi2: TextView? = null
    private var tv_aqi3: TextView? = null
    private var tv_aqi4: TextView? = null
    private var tv_aqi5: TextView? = null

    private var tv_pm1: TextView? = null
    private var tv_pm2: TextView? = null
    private var tv_pm3: TextView? = null
    private var tv_pm4: TextView? = null
    private var tv_pm5: TextView? = null
    private var tv_pm6: TextView? = null

    init {

        // 设置去头
        this@AqiDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = View.inflate(mContext, R.layout.dialog_aqi, null)
        setContentView(view)
        // 点击dialog外部关闭对话框
        this@AqiDialog.setCanceledOnTouchOutside(true)
        // 在布局文件中设置background后设置这个background才有显示效果
        this@AqiDialog.window!!.setBackgroundDrawableResource(R.color.white)

        tv_aqi1 = view.findViewById(R.id.tv_aqi1)
        tv_aqi2 = view.findViewById(R.id.tv_aqi2)
        tv_aqi3 = view.findViewById(R.id.tv_aqi3)
        tv_aqi4 = view.findViewById(R.id.tv_aqi4)
        tv_aqi5 = view.findViewById(R.id.tv_aqi5)

        tv_pm1 = view.findViewById(R.id.tv_pm1)
        tv_pm2 = view.findViewById(R.id.tv_pm2)
        tv_pm3 = view.findViewById(R.id.tv_pm3)
        tv_pm4 = view.findViewById(R.id.tv_pm4)
        tv_pm5 = view.findViewById(R.id.tv_pm5)
        tv_pm6 = view.findViewById(R.id.tv_pm6)
    }

    fun setApiTextColor(apiNum: String) {
        when {
            apiNum.toInt() in 0..50 -> settTvColor(tv_aqi1, tv_aqi2, tv_aqi3, tv_aqi4, tv_aqi5, tv_aqi5)
            apiNum.toInt() in 51..100 -> settTvColor(tv_aqi2, tv_aqi1, tv_aqi3, tv_aqi4, tv_aqi5, tv_aqi5)
            apiNum.toInt() in 101..150 -> settTvColor(tv_aqi3, tv_aqi1, tv_aqi2, tv_aqi4, tv_aqi5, tv_aqi5)
            apiNum.toInt() in 151..200 -> settTvColor(tv_aqi4, tv_aqi1, tv_aqi2, tv_aqi3, tv_aqi5, tv_aqi5)
            apiNum.toInt() in 201..300 -> settTvColor(tv_aqi5, tv_aqi1, tv_aqi2, tv_aqi3, tv_aqi4, tv_aqi4)
        }
    }

    fun setPmTextColor(pmNum: String) {
        when {
            pmNum.toInt() in 0..35 -> settTvColor(tv_pm1, tv_pm2, tv_pm3, tv_pm4, tv_pm5, tv_pm6)
            pmNum.toInt() in 36..75 -> settTvColor(tv_pm2, tv_pm1, tv_pm3, tv_pm4, tv_pm5, tv_pm6)
            pmNum.toInt() in 76..115 -> settTvColor(tv_pm3, tv_pm1, tv_pm2, tv_pm4, tv_pm5, tv_pm6)
            pmNum.toInt() in 116..150 -> settTvColor(tv_pm4, tv_pm1, tv_pm2, tv_pm3, tv_pm5, tv_pm6)
            pmNum.toInt() in 151..250 -> settTvColor(tv_pm5, tv_pm1, tv_pm2, tv_pm3, tv_pm4, tv_pm6)
            pmNum.toInt() > 250 -> settTvColor(tv_pm6, tv_pm1, tv_pm2, tv_pm3, tv_pm4, tv_pm5)
        }
    }

    private fun settTvColor(tv1: TextView?, tv2: TextView?, tv3: TextView?, tv4: TextView?, tv5: TextView?, tv6: TextView?) {
        tv1?.setTextColor(mContext.resources.getColor(R.color.refresh_blue))
        tv2?.setTextColor(mContext.resources.getColor(R.color.black))
        tv3?.setTextColor(mContext.resources.getColor(R.color.black))
        tv4?.setTextColor(mContext.resources.getColor(R.color.black))
        tv5?.setTextColor(mContext.resources.getColor(R.color.black))
        tv6?.setTextColor(mContext.resources.getColor(R.color.black))
    }
}
