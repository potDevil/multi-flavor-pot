package com.kotlin.activity

import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.View
import com.kotlin.R
import com.kotlin.common.WEBVIEW_TITLE
import com.kotlin.common.WEBVIEW_URL
import com.kotlin.view.TitleView
import com.kotlin.view.webview.ProgressWebView
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import java.util.*

/**
 * Created by fzh on 2018/4/11.
 */
class X5WebViewActivity : BaseActivity() {
    private var title = "WebView"
    private var url = "http://www.baidu.cn/"
    private var title_view: TitleView? = null
    private var web_view: ProgressWebView? = null
    private var webSettings: WebSettings? = null
    private var webChromeClient: WebChromeClient? = null

    companion object {
        fun start(context: Context, title: String?, url: String?) {
            val intent = Intent(context, X5WebViewActivity::class.java)
            intent.putExtra(WEBVIEW_TITLE, title)
            intent.putExtra(WEBVIEW_URL, url)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        // 网页中的视频，上屏幕的时候，可能出现闪烁的情况，需要如下设置
        window.setFormat(PixelFormat.TRANSLUCENT)
        title = intent.getStringExtra(WEBVIEW_TITLE)
        url = intent.getStringExtra(WEBVIEW_URL)

        initView()
        initWebView()
    }

    private fun initView() {
        title_view = findViewById(R.id.title_view) as TitleView
        title_view?.setTextTitle(title)
    }

    private fun initWebView() {
        web_view = findViewById(R.id.web_view) as ProgressWebView
        web_view?.isDrawingCacheEnabled = true
        webChromeClient = WebChromeClient()
        web_view?.webChromeClient = webChromeClient
        webSettings = web_view?.settings

        // 网页内容的宽度是否可大于WebView控件的宽度
        webSettings?.loadWithOverviewMode = false
        // 保存表单数据
        webSettings?.saveFormData = true
        webSettings?.cacheMode = WebSettings.LOAD_NO_CACHE //关闭webview中缓存
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        webSettings?.setSupportZoom(true)
        webSettings?.builtInZoomControls = true
        //隐藏原生的缩放控件
        webSettings?.displayZoomControls = false

        web_view?.requestFocus() //此句可使html表单可以接收键盘输入
        web_view?.isFocusable = true
        webSettings?.useWideViewPort = true
        webSettings?.savePassword = true
        webSettings?.setGeolocationEnabled(true)
        webSettings?.domStorageEnabled = true
        webSettings?.javaScriptEnabled = true
        // 启动应用缓存
        webSettings?.setAppCacheEnabled(false)
        // 设置缓存模式
        webSettings?.cacheMode = WebSettings.LOAD_NO_CACHE
        // 设置此属性，可任意比例缩放。
        webSettings?.useWideViewPort = true
        webSettings?.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。
        webSettings?.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
        //  页面加载好以后，再放开图片
        //mSettings.setBlockNetworkImage(false);
        // 使用localStorage则必须打开
        webSettings?.domStorageEnabled = true
        // 排版适应屏幕
        webSettings?.layoutAlgorithm = com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        // WebView是否支持多个窗口。
        webSettings?.setSupportMultipleWindows(true)
        webSettings?.useWideViewPort = true // 关键点
        webSettings?.allowFileAccess = true // 允许访问文件
        //将图片调整到适合webview的大小
        webSettings?.useWideViewPort = true
        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }
        // 缩放至屏幕的大小
        webSettings?.loadWithOverviewMode = true
        //其他细节操作
        webSettings?.allowFileAccess = true //设置可以访问文件
        webSettings?.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
        webSettings?.loadsImagesAutomatically = true //支持自动加载图片
        webSettings?.defaultTextEncodingName = "utf-8"//设置编码格式
        webSettings?.domStorageEnabled = true//JS在HTML里面设置了本地存储localStorage，java中使用localStorage则必须打开
        web_view?.settings?.domStorageEnabled = true
        web_view?.settings?.useWideViewPort = true //自适应屏幕

        //以下接口禁止(直接或反射)调用，避免视频画面无法显示：
        //webView.setLayerType();
        web_view?.isDrawingCacheEnabled = true

        //去除QQ浏览器推广广告
        window.decorView.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            val outView = ArrayList<View>()
            window.decorView.findViewsWithText(outView, "QQ浏览器", View.FIND_VIEWS_WITH_TEXT)
            if (outView.size > 0) {
                outView[0].visibility = View.GONE
            }
        }

        web_view?.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(webView: WebView?, s: String?): Boolean {
                webView?.loadUrl(s)
                return true
            }

        }

        web_view?.loadUrl(url)

    }
}