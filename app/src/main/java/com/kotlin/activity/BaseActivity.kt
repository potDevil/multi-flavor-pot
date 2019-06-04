package com.kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import com.kotlin.service.presenter.Presenter
import com.kotlin.service.view.BaseView
import org.greenrobot.eventbus.EventBus

/**
 * Created by fuzhihuang on 2017/9/14.
 */

abstract class BaseActivity<T : Presenter, V : BaseView> : AppCompatActivity() {

    var basePresenter: T? = null
    var baseView: V? = null

    protected abstract fun initRequest()

    // lateinit property toast has not been initialized 懒加载还未被初始化
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRequest()
        basePresenter?.onCreate()
        basePresenter?.attachView(baseView)
    }

    fun startActivity(activity: Class<out AppCompatActivity>) {
        val intent = Intent(this@BaseActivity, activity)
        startActivity(intent)
    }

    fun showToast(s: String) {
        if (TextUtils.isEmpty(s))
            return
        toast?.cancel()
        toast = Toast.makeText(this, s, Toast.LENGTH_SHORT)
        toast?.show()
    }

    //    @Override
    //    public void onBackPressed() {
    //        //按返回键返回桌面
    //        Intent intent = new Intent(Intent.ACTION_MAIN);
    //        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //        intent.addCategory(Intent.CATEGORY_HOME);
    //        startActivity(intent);
    //    }
    //    @Override
    //    public void onBackPressed() {
    //        //按返回键返回桌面
    //        moveTaskToBack(true);
    //    }

    override fun onDestroy() {
        super.onDestroy()
        basePresenter?.onStop()
        EventBus.getDefault().unregister(this)
    }
}
