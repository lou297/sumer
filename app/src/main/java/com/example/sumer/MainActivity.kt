package com.example.sumer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.sumer.util.UrlConstants
import com.example.sumer.util.LawAIWebViewClient
import com.example.sumer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initWebView()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val webView = binding.webview
        var webViewUrl = StringBuilder(UrlConstants.LAW_AI_BASE_URL)
        when (item.itemId) {
            R.id.home_btn -> {
                webViewUrl.append(UrlConstants.HOME)
            }
            R.id.my_page_btn -> {
                webViewUrl.append(UrlConstants.MY_PAGE)
            }
            R.id.setting_btn -> {
                webViewUrl.append(UrlConstants.SETTING)
            }
        }

        webView.loadUrl(webViewUrl.toString())
        return super.onOptionsItemSelected(item)
    }

    fun initWebView() {
        val webView = binding.webview
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.setPadding(0, 0, 0, 0)
        webSettings.useWideViewPort = true
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true

        webView.webViewClient = LawAIWebViewClient(this)

        val setting = getSharedPreferences("setting", 0)
        val cred = setting.getString("cred", "")
        if ("" == cred == false) {
            val authHeader = "Basic $cred"
            val headers: MutableMap<String, String> = HashMap()
            headers["Authorization"] = authHeader
            //mWebView.loadUrl(myUrl + "/account/login.mo", headers);
            webView.loadUrl(UrlConstants.LAW_AI_BASE_URL + "/main.mo", headers)
        } else {
            webView.loadUrl(UrlConstants.LAW_AI_BASE_URL + "/main.mo")
        }
    }
}