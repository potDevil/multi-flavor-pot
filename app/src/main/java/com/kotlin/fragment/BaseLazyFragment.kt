package com.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.widget.Toast
import com.kotlin.service.presenter.Presenter
import org.greenrobot.eventbus.EventBus
import com.kotlin.service.view.BaseView


/**
 * Created by wentong.chen on 2017/11/7.
 * 懒加载fragment
 */

abstract class BaseLazyFragment<T : Presenter, V : BaseView> : Fragment() {

    var basePresenter: T? = null
    var baseView: V? = null
    private var isPrepared: Boolean = false
    private var isInit: Boolean = false

//    protected abstract fun initPresenter(): T

    protected abstract fun initRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        basePresenter = initPresenter()
        initRequest()
        basePresenter?.onCreate()
        basePresenter?.attachView(baseView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isPrepared = true
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        userVisibleHint = !hidden
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isPrepared && !isInit) {
            isInit = true
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

    private var toast: Toast? = null

    fun showToast(s: String) {
        if (TextUtils.isEmpty(s))
            return
        if (toast != null) {
            toast?.cancel()
        }
        toast = Toast.makeText(context, s, Toast.LENGTH_SHORT)
        toast?.show()
    }
}
