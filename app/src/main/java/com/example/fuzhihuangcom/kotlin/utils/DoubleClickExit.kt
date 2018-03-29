package com.example.fuzhihuangcom.kotlin.utils

/**
 * Created by HugoXie on 16/6/25.
 *
 * Email: Hugo3641@gamil.com
 * GitHub: https://github.com/xcc3641
 * Info:
 */
object DoubleClickExit {
    /**
     * 双击退出检测, 阈值 1000ms
     */
    var mLastClick = 0L
    private val THRESHOLD = 2000// 1000ms

    fun check(): Boolean {
        val now = System.currentTimeMillis()
        val b = now - mLastClick < THRESHOLD
        mLastClick = now
        return b
    }
}
