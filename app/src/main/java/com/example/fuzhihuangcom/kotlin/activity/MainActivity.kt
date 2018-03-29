package com.example.fuzhihuangcom.kotlin.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.example.fuzhihuangcom.kotlin.R
import com.example.fuzhihuangcom.kotlin.fragment.CateFragment
import com.example.fuzhihuangcom.kotlin.fragment.DFragment
import com.example.fuzhihuangcom.kotlin.fragment.GirlFragment
import com.example.fuzhihuangcom.kotlin.fragment.WeatherFragment
import com.example.fuzhihuangcom.kotlin.utils.DoubleClickExit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var fragmentList: MutableList<Fragment> = ArrayList<Fragment>();
    private lateinit var weatherFragment: WeatherFragment
    private lateinit var cateFragment: CateFragment
    private lateinit var girlFragment: GirlFragment
    private lateinit var dFragment: DFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        initView()
    }

    private fun initFragment() {
        weatherFragment = WeatherFragment()
        cateFragment = CateFragment()
        girlFragment = GirlFragment()
        dFragment = DFragment()
        fragmentList.add(weatherFragment)
        fragmentList.add(cateFragment)
        fragmentList.add(girlFragment)
        fragmentList.add(dFragment)
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fl_container, weatherFragment)
                .add(R.id.fl_container, cateFragment)
                .add(R.id.fl_container, girlFragment)
                .add(R.id.fl_container, dFragment)
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
                R.id.menu_girl -> switchFragment(girlFragment)
                R.id.menu_about -> switchFragment(dFragment)
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
