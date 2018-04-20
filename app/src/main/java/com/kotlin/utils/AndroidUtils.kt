package com.kotlin.utils

import android.content.Context

/**
 * Created by fzh on 2018/1/23.
 */
class AndroidUtils {
    companion object {
        fun dip2px(context: Context, dipValue: Float): Int {
            val scale: Float = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }
    }
}