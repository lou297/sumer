package com.example.sumer.util

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient

class LawAIWebViewClient(val context: Context) : WebViewClient() {
    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        val startHttp = "://callmethod/?action="

        var url = request?.url.toString()
        if (url != null && url.contains(startHttp)) {
            url = url.substring(url.indexOf(startHttp))
            url = url.replace(startHttp, "")
            if (url.startsWith("saveCredential")) {
                val cred: String = url.replace("saveCredential&cred=", "")
                if (cred.isEmpty() == false) {
                    Log.d("kk", "save credential!")
                    val setting: SharedPreferences = context.getSharedPreferences("setting", 0)
                    val editor = setting.edit()
                    editor.putString("cred", cred)
                    editor.commit()
                }
            } else if (url.startsWith("deleteCredential")) {
                Log.d("kk", "delete credential!")
                val setting: SharedPreferences = context.getSharedPreferences("setting", 0)
                val editor = setting.edit()
                editor.remove("cred")
                editor.commit()
            } else if (url.startsWith("openBrowser")) {
                val urlStr: String = url.replace("openBrowser&urlStr=", "")
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(urlStr))
                context.startActivity(i)
            }
        }

        return super.shouldInterceptRequest(view, request)
    }
}