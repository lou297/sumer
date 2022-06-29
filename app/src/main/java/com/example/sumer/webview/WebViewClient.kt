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

class WebViewClient(val context: Context) : WebViewClient() {
    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        val startHttp = "://callmethod/?action="

        val requestUrl = request?.url.toString()
        if (requestUrl.contains(startHttp)) {
            var subUrl = requestUrl.substring(requestUrl.indexOf(startHttp))
            subUrl = subUrl.replace(startHttp, Constants.EMPTY_STRING)
            if (subUrl.startsWith(Constants.SAVE_CREDENTIAL)) {
                val cred: String = subUrl.replace("${Constants.SAVE_CREDENTIAL}&${Constants.AUTH_CREDENTIAL}=", Constants.EMPTY_STRING)
                if (!cred.isEmpty()) {
                    val setting: SharedPreferences = context.getSharedPreferences(Constants.SETTING, 0)
                    val editor = setting.edit()
                    editor.putString(Constants.AUTH_CREDENTIAL, cred).commit()
                }
            }
            if (subUrl.startsWith(Constants.DELETE_CREDENTIAL)) {
                val setting: SharedPreferences = context.getSharedPreferences(Constants.SETTING, 0)
                val editor = setting.edit()
                editor.remove(Constants.AUTH_CREDENTIAL).commit()
            }
            if (subUrl.startsWith(Constants.OPEN_BROWSER)) {
                val urlStr: String = subUrl.replace("${Constants.OPEN_BROWSER}&urlStr=", Constants.EMPTY_STRING)
                val intentForWebview = Intent(Intent.ACTION_VIEW, Uri.parse(urlStr))
                context.startActivity(intentForWebview)
            }
        }

        return super.shouldInterceptRequest(view, request)
    }
}