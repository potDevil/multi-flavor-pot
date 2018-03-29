package com.example.fuzhihuangcom.kotlin.activity

import android.app.Activity
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.text.TextUtils
import android.widget.Toast

/**
 * Created by fuzhihuang on 2017/9/14.
 */

open class BaseActivity : FragmentActivity() {
    // lateinit property mToast has not been initialized 懒加载还未被初始化
    private var mToast: Toast? = null

    fun startActivity(activity: Class<out Activity>) {
        val intent = Intent(this@BaseActivity, activity)
        startActivity(intent)
    }

    fun showToast(s: String) {
        if (TextUtils.isEmpty(s))
            return
        mToast?.cancel()
        mToast = Toast.makeText(this, s, Toast.LENGTH_SHORT)
        mToast?.show()
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
}
