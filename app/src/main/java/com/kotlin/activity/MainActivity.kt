package com.kotlin.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.kotlin.R
import com.kotlin.fragment.AboutFragment
import com.kotlin.fragment.CateFragment
import com.kotlin.fragment.WeChatChoicenessFragment
import com.kotlin.fragment.WeatherFragment
import com.kotlin.utils.DoubleClickExit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var fragmentList: MutableList<Fragment> = ArrayList<Fragment>();
    private lateinit var weatherFragment: WeatherFragment
    private lateinit var cateFragment: CateFragment
//    private lateinit var girlFragment: GirlFragment
    private lateinit var weChatChoicenessFragment: WeChatChoicenessFragment
    private lateinit var aboutFragment: AboutFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        initView()
    }

    private fun initFragment() {
        weatherFragment = WeatherFragment()
        cateFragment = CateFragment()
//        girlFragment = GirlFragment()
        weChatChoicenessFragment = WeChatChoicenessFragment()
        aboutFragment = AboutFragment()
        fragmentList.add(weatherFragment)
        fragmentList.add(cateFragment)
        fragmentList.add(weChatChoicenessFragment)
//        fragmentList.add(girlFragment)
        fragmentList.add(aboutFragment)
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fl_container, weatherFragment)
                .add(R.id.fl_container, cateFragment)
                .add(R.id.fl_container, weChatChoicenessFragment)
//                .add(R.id.fl_container, girlFragment)
                .add(R.id.fl_container, aboutFragment)
                .commitAllowingStateLoss()
        switchFragment(weatherFragment)
    }

    private fun switchFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        for (it in fragmentList) {
            if (it == fragment) {
                fragmentTransaction.show(fragment)
            } else {
                fragmentTransaction.hide(it)
            }
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    private fun initView() {
        bnv.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_android -> switchFragment(weatherFragment)
                R.id.menu_ios -> switchFragment(cateFragment)
                R.id.menu_wechat -> switchFragment(weChatChoicenessFragment)
                R.id.menu_about -> switchFragment(aboutFragment)
            }
            false
        }
    }

    override fun onBackPressed() {
//        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
//            mDrawerLayout.closeDrawer(GravityCompat.START)
//        } else {
        if (!DoubleClickExit.check()) {
            showToast(getString(R.string.double_exit))
        } else {
            finish()
        }
//        }
    }
}
