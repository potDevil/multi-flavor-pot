package com.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.widget.Toast
import com.kotlin.service.presenter.BasePresenter
import com.kotlin.service.presenter.Presenter
import org.greenrobot.eventbus.EventBus
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.kotlin.service.view.View


/**
 * Created by wentong.chen on 2017/11/7.
 * 懒加载fragment
 */

abstract class BaseLazyFragment<T : Presenter> : Fragment() {
    var basePresenter: T? = null
    private var mIsPrepared: Boolean = false
    private var mIsInit: Boolean = false

//    protected abstract fun initPresenter(): T

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        basePresenter = initPresenter()
//    }

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

    override fun onDestroy() {
        super.onDestroy()
        basePresenter?.onStop()
        EventBus.getDefault().unregister(this)
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
