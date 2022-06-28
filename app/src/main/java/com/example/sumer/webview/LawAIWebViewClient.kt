package com.example.sumer.webview

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.sumer.util.Constants
import com.example.sumer.util.Constants.DELETE_CREDENTIAL

class LawAIWebViewClient(val context: Context) : WebViewClient() {
    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        val startHttp = "://callmethod/?action="

        val requestUrl = request?.url.toString()
        if (requestUrl.contains(startHttp)) {
            var subUrl = requestUrl.substring(requestUrl.indexOf(startHttp))
            subUrl = subUrl.replace(startHttp, "")
            if (subUrl.startsWith(Constants.SAVE_CREDENTIAL)) {
                val cred: String = subUrl.replace("${Constants.SAVE_CREDENTIAL}&${Constants.AUTH_CREDENTIAL}=", "")
                if (!cred.isEmpty()) {
                    Log.d("kk", "save credential!")
                    val setting: SharedPreferences = context.getSharedPreferences(Constants.SETTING, 0)
                    val editor = setting.edit()
                    editor.putString(Constants.AUTH_CREDENTIAL, cred)
                    editor.commit()
                }
            } else if (subUrl.startsWith(Constants.DELETE_CREDENTIAL)) {
                Log.d("kk", "delete credential!")
                val setting: SharedPreferences = context.getSharedPreferences(Constants.SETTING, 0)
                val editor = setting.edit()
                editor.remove(Constants.AUTH_CREDENTIAL)
                editor.commit()
            } else if (subUrl.startsWith(Constants.OPEN_BROWSER)) {
                val urlStr: String = subUrl.replace("${Constants.OPEN_BROWSER}&urlStr=", "")
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(urlStr))
                context.startActivity(i)
            }
        }

        return super.shouldInterceptRequest(view, request)
    }
}