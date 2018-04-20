package com.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.widget.Toast

/**
 * Created by wentong.chen on 2017/11/7.
 * 懒加载fragment
 */

abstract class BaseLazyFragment : Fragment() {
    private var mIsPrepared: Boolean = false
    private var mIsInit: Boolean = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mIsPrepared = true
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        userVisibleHint = !hidden
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mIsPrepared && !mIsInit) {
            mIsInit = true
            lazyLoadData()
        }
    }

    /**
     * 懒加载数据
     */
    protected abstract fun lazyLoadData()

    private var mToast: Toast? = null

    fun showToast(s: String) {
        if (TextUtils.isEmpty(s))
            return
        if (mToast != null) {
            mToast?.cancel()
        }
        mToast = Toast.makeText(context, s, Toast.LENGTH_SHORT)
        mToast?.show()
    }
}
