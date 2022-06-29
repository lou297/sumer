package com.example.sumer

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.sumer.databinding.ActivityMainBinding
import com.example.sumer.firebase.FirebaseMessagingService
import com.example.sumer.stt.SpeechRecognitionListener
import com.example.sumer.util.*
import com.example.sumer.webview.WebViewClient

class MainActivity : AppCompatActivity() {
    public val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val speechRecognizer : SpeechRecognizer by lazy {
        SpeechRecognizer.createSpeechRecognizer(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initWebView()
        initSpeechToTextSetting()
        initFcm()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val webView = binding.webview
        var webViewUrl = StringBuilder(UrlConstants.LAW_AI_BASE_URL)
        when (item.itemId) {
            R.id.mike_btn -> {
                webViewUrl.clear(); webViewUrl.append(Constants.PRE_VOICE)
                speechRecognizer.startListening(intentForRecognizer())
            }
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

    private fun initWebView() {
        val webView = binding.webview
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.setPadding(0, 0, 0, 0)
        webSettings.useWideViewPort = true
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webView.webViewClient = WebViewClient(this)

        val cred = getSharedPreferences(Constants.SETTING, 0).getString(Constants.AUTH_CREDENTIAL, null)
        val requestUrl = UrlConstants.LAW_AI_BASE_URL + UrlConstants.HOME

        if (cred != null) {
            val headers: MutableMap<String, String> = HashMap()
            headers[Constants.AUTHENTICATION] = "Basic $cred"
            webView.loadUrl(requestUrl, headers)
        } else {
            webView.loadUrl(requestUrl)
        }
    }

    private fun initSpeechToTextSetting() {
        //음성인식 초기화.
        PermissionCheck(this).requestRecordAudioPermission()
        speechRecognizer.setRecognitionListener(SpeechRecognitionListener(this))
    }

    private fun initFcm() {
        val fcm = Intent(applicationContext, FirebaseMessagingService::class.java)
        startService(fcm)
    }

    private fun intentForRecognizer() : Intent {
        val recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, packageName)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Constants.KR)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)

        return recognizerIntent
    }


}