package com.plampay.sdk.demo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Handler
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView

object Utils {

    @SuppressLint("SetJavaScriptEnabled")
    fun clear( url:String,application: Application){
        val webView = WebView(application)
        webView.settings.apply {
            javaScriptCanOpenWindowsAutomatically = true
            cacheMode = WebSettings.LOAD_DEFAULT
            allowFileAccess = true
            domStorageEnabled = true
            databaseEnabled = true
            setSupportZoom(true)
            javaScriptEnabled = true
        }
        webView.loadUrl(url)
        Handler().postDelayed({
            webView.evaluateJavascript("(function() { window.localStorage.clear();\n" +
                    "          return '清除成功'; })();"
            ) { value ->
                Log.d(">>>>", value!!) // "Hello, World!"
            }
        },500)
    }
}