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

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: WebActivityBinding

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
                    return true
                } else if (url?.startsWith("pay://") == true) {
                    try {
                        val it = Intent(Intent.ACTION_VIEW)
                        it.setData(Uri.parse(request.url?.toString()))
                        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(it)
                    } catch (e: Exception) {
                        //需要自己处理逻辑
                        Toast.makeText(this@WebViewActivity, "拉端失败", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                    return true
                }
                return super.shouldOverrideUrlLoading(view, request)
            }

        }

        binding.webView.loadUrl("https://checkout-daily.palmpay.com/h5-checkout/?countryCode=NG&payToken=2A0193D7DF49C88ABA359FA81E355804&orderNo=2424240118074501987143&signKey=6NIlvTDXPBrsmZLt5kzEJChrwc86AcIaBXFyTq0hVS4&signSession=2424240118074501987143&appId=L231127054769719185611&productType=pay_wallet&callable=true")
    }
}