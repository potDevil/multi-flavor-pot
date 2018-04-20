package com.kotlin.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import android.widget.*
import com.kotlin.R
import com.kotlin.adapter.OtherAdapter
import com.kotlin.adapter.UserAdapter
import com.kotlin.common.ERROR_TIP
import com.kotlin.common.USER_CATEGORY
import com.kotlin.common.WECHAT_USER_INFO
import com.kotlin.service.bean.wechat.WeChatCategoryInfo
import com.kotlin.service.presenter.WeChatCategoryPresenter
import com.kotlin.service.view.WeChatCategoryView
import com.kotlin.view.TitleView
import com.kotlin.view.webview.DragGrid
import com.kotlin.view.webview.OtherGridView
import com.orhanobut.hawk.Hawk
import java.util.*

/**
 * Created by fzh on 2018/4/12.
 */
class WeChatCategoryActivity : BaseActivity(), AdapterView.OnItemClickListener {

    private lateinit var weChatCategoryPresenter: WeChatCategoryPresenter

    private var otherCategoryList = ArrayList<WeChatCategoryInfo>()
    private var userCategoryList = ArrayList<WeChatCategoryInfo>()

    private var title_view: TitleView? = null
    private var user_grid_view: DragGrid? = null
    private var other_grid_view: OtherGridView? = null

    private var userAdapter: UserAdapter? = null
    private var otherAdapter: OtherAdapter? = null

    private var isMove = false
    private var isDataChanged = false

    private var weChatCategoryView = object : WeChatCategoryView {
        override fun onLoadWeChatDataSuccess(t: List<WeChatCategoryInfo>?) {
            otherCategoryList = t as ArrayList<WeChatCategoryInfo>
            if (!Hawk.get<ArrayList<WeChatCategoryInfo>>(WECHAT_USER_INFO, ArrayList()).isEmpty() && !otherCategoryList.isEmpty()) {
                val weChatCacheInfo = Hawk.get<ArrayList<WeChatCategoryInfo>>(WECHAT_USER_INFO, ArrayList())
                (0 until weChatCacheInfo.size).forEach {
                    otherCategoryList.remove(weChatCacheInfo[it])

                }
            }
            otherAdapter?.setListDate(otherCategoryList)
        }

        override fun onLoadWeChatDataError(s: String?) {
            showToast(ERROR_TIP)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wechat_activity)
        setResult(RESULT_OK, intent)

        bindRequest()
        weChatCategoryPresenter.getWeChatCategory()

        userCategoryList = intent.getSerializableExtra(USER_CATEGORY) as ArrayList<WeChatCategoryInfo>

        title_view = findViewById(R.id.title_view) as TitleView
        user_grid_view = findViewById(R.id.user_grid_view) as DragGrid
        other_grid_view = findViewById(R.id.other_grid_view) as OtherGridView

        title_view?.setTextTitle("微信频道管理")

        userAdapter = UserAdapter(this, userCategoryList)
        user_grid_view?.adapter = userAdapter

        otherAdapter = OtherAdapter(this, otherCategoryList)
        other_grid_view?.adapter = otherAdapter

        other_grid_view?.onItemClickListener = this
        user_grid_view?.onItemClickListener = this
    }

    private fun bindRequest() {
        weChatCategoryPresenter = WeChatCategoryPresenter(this)
        weChatCategoryPresenter.onCreate()
        weChatCategoryPresenter.attachView(weChatCategoryView)
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        if (isMove) {
            return
        }

        when (parent.id) {
            R.id.user_grid_view -> {
                isDataChanged = true
                if (position != 0 && position != 1) {
                    val moveImageView = getView(view)

                    val newTextView = view.findViewById<View>(R.id.text_item) as TextView
                    val startLocation = IntArray(2)
                    newTextView.getLocationInWindow(startLocation)
                    val channel = (parent.adapter as UserAdapter).getItem(position)

                    otherAdapter?.isVisible = false
                    otherAdapter?.addItem(channel)

                    Handler().postDelayed({
                        try {
                            val endLocation = IntArray(2)

                            other_grid_view?.lastVisiblePosition?.let { other_grid_view?.getChildAt(it)?.getLocationInWindow(endLocation) }
                            moveAnim(moveImageView, startLocation, endLocation, user_grid_view as GridView)
                            userAdapter?.setRemove(position)

                        } catch (localException: Exception) {
                        }
                    }, 50L)
                }
            }
            R.id.other_grid_view -> {
                isDataChanged = true

                val moveImageView = getView(view)
                val newTextView = view.findViewById<View>(R.id.text_item) as TextView
                val startLocation = IntArray(2)
                newTextView.getLocationInWindow(startLocation)
                val channel = (parent.adapter as OtherAdapter).getItem(position)

                userAdapter?.isVisible = false
                userAdapter?.addItem(channel)

                Handler().postDelayed({
                    try {
                        val endLocation = IntArray(2)

                        user_grid_view?.lastVisiblePosition?.let { user_grid_view?.getChildAt(it)?.getLocationInWindow(endLocation) }
                        moveAnim(moveImageView, startLocation, endLocation, other_grid_view as GridView)
                        otherAdapter?.setRemove(position)
                    } catch (localException: Exception) {
                    }
                }, 50L)
            }
        }
    }

    private fun saveItemInfo() {
        Hawk.put(WECHAT_USER_INFO, userAdapter?.getCategoryInfoList())
    }

    private fun getView(view: View): ImageView {
        view.destroyDrawingCache()
        view.isDrawingCacheEnabled = true
        val cache = Bitmap.createBitmap(view.drawingCache)
        view.isDrawingCacheEnabled = false
        val iv = ImageView(this)
        iv.setImageBitmap(cache)
        return iv
    }

    private fun moveAnim(moveView: View, startLocation: IntArray, endLocation: IntArray, clickGridView: GridView) {
        val initLocation = IntArray(2)
        moveView.getLocationInWindow(initLocation)
        val moveViewGroup = getMoveViewGroup()
        val mMoveView = getMoveView(moveViewGroup, moveView, initLocation)

        val moveAnimation = TranslateAnimation(
                startLocation[0].toFloat(), endLocation[0].toFloat(), startLocation[1].toFloat(),
                endLocation[1].toFloat())
        moveAnimation.duration = 300L

        val moveAnimationSet = AnimationSet(true)
        moveAnimationSet.fillAfter = false
        moveAnimationSet.addAnimation(moveAnimation)
        mMoveView.startAnimation(moveAnimationSet)
        moveAnimationSet.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {
                isMove = true
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                moveViewGroup.removeView(mMoveView)

                if (clickGridView is DragGrid) {
                    otherAdapter?.isVisible = true
                    otherAdapter?.notifyDataSetChanged()
                    userAdapter?.remove()
                } else {
                    userAdapter?.isVisible = true
                    userAdapter?.notifyDataSetChanged()
                    otherAdapter?.remove()
                }
                // userAdapter & otherAdapter 移动后 重新保存list
                saveItemInfo()
                isMove = false
            }
        })
    }

    private fun getMoveViewGroup(): ViewGroup {
        val moveViewGroup = window.decorView as ViewGroup
        val moveLinearLayout = LinearLayout(this)
        moveLinearLayout.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        moveViewGroup.addView(moveLinearLayout)
        return moveLinearLayout
    }

    private fun getMoveView(viewGroup: ViewGroup, view: View, initLocation: IntArray): View {
        val x = initLocation[0]
        val y = initLocation[1]
        viewGroup.addView(view)
        val mLayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        mLayoutParams.leftMargin = x
        mLayoutParams.topMargin = y
        view.layoutParams = mLayoutParams
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        weChatCategoryPresenter.onStop()
    }
}