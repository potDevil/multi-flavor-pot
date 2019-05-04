package com.kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.kotlin.R
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by fzh on 2018/1/25.
 */
class SplashActivity : BaseActivity() {

    private var alphaAnimation: AlphaAnimation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (isLauncherStart()) {
            return
        }

        jump2main()
    }

    private fun jump2main() {
//        Handler().postDelayed({
//            startActivity(MainActivity::class.java)
//            finish()
//        }, 2500)
        //渐变展示启动屏
        alphaAnimation = AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2500
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                startActivity(MainActivity::class.java)
                finish()
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {}

        })
        layout_splash.startAnimation(alphaAnimation)
    }

    private fun isLauncherStart(): Boolean {
        if (!isTaskRoot) {
            val mainIntent: Intent = intent
            val action: String = mainIntent.action
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action == Intent.ACTION_MAIN) {
                finish()
                return true
            }
        }
        return false
    }
}