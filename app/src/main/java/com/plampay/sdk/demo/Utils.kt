package com.plampay.sdk.demo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView

object Utils {

    @SuppressLint("SetJavaScriptEnabled")
    fun clear(context: Context){
        val webView = WebView(context)
        webView.settings.apply {
            javaScriptCanOpenWindowsAutomatically = true
            cacheMode = WebSettings.LOAD_DEFAULT
            allowFileAccess = true
            domStorageEnabled = true
            databaseEnabled = true
            setSupportZoom(true)
            javaScriptEnabled = true
        }
        webView.loadUrl("file:///android_asset/Test.html")
        Handler().postDelayed({
            webView.evaluateJavascript("(function() { window.localStorage.clear();\n" +
                    "          return 'Hello, World!'; })();"
            ) { value ->
                Log.d(">>>>", value!!) // "Hello, World!"
            }
        },500)
    }
}