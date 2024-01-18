package com.plampay.sdk.demo

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.plampay.sdk.demo.databinding.WebActivityBinding

class WebViewActivity: AppCompatActivity() {

    private lateinit var  binding :WebActivityBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WebActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.settings.apply {
            javaScriptCanOpenWindowsAutomatically = true
            cacheMode = WebSettings.LOAD_DEFAULT
            allowFileAccess = true
            domStorageEnabled = true
            databaseEnabled = true
            setSupportZoom(true)
            javaScriptEnabled = true
        }


        binding.webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url?.toString()
                if (url?.startsWith("http") == true) {
                    view?.loadUrl(url)
                    return super.shouldOverrideUrlLoading(view, request)
                } else if (url?.startsWith("pay://") == true) {
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    }catch (e:Exception){
                        //需要自己处理逻辑
                        Toast.makeText(this@WebViewActivity,"拉端失败", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }

                    return true
                }
                return super.shouldOverrideUrlLoading(view, request)
            }

        }
        binding.webView.loadUrl("https://juejin.cn/?utm_source=gold_browser_extension")
    }
}