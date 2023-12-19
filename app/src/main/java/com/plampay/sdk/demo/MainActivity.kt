package com.plampay.sdk.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.plampay.sdk.demo.databinding.MainLayoutBinding
import com.transsnet.gcd.sdk.CashierDesk
import com.transsnet.gcd.sdk.config.Constants
import com.transsnet.gcd.sdk.config.PayReq
import com.transsnet.gcd.sdk.config.Result
import com.transsnet.gcd.sdk.http.Api
import com.transsnet.gcd.sdk.http.HttpApi
import com.transsnet.gcd.sdk.http.req.PreCreateOrderReq
import com.transsnet.gcd.sdk.http.resp.PreCreateOrderResp
import com.transsnet.gcd.sdk.net.able.Cancelable
import com.transsnet.gcd.sdk.net.rest.Request
import com.transsnet.gcd.sdk.util.InitUtil
import com.transsnet.gcd.sdk.util.ToastUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.payButton.setOnClickListener {
            payOrder()
        }
    }


    private fun payOrder() {
        val req = PayReq()
        //国家码 非必传
        req.country = Constants.COUNTRY_NG

        //手机号码 非必传
        req.phone = "08123456789"

        //付款产品名称 必传
        req.productName = "Merchant payment"


        //sdkSignKey 由后台生成
        req.sdkSignKey = ""


        //订单金额
        req.orderAmount = 100


        //payToken 由后台生成
        req.payToken = ""


        //sdkSessionId 由后台生成
        req.sdkSessionId = ""



        CashierDesk.pay(
            req
        ) { result ->
            when (result.resultCode) {
                Result.RESULT_CODE_SUCCESS -> {
                    //支付成功
                    Toast.makeText(this@MainActivity, "RESULT_CODE_SUCCESS", Toast.LENGTH_LONG).show()
                }
                Result.RESULT_CODE_FAIL -> {
                    //支付失败
                    Toast.makeText(this@MainActivity, "RESULT_CODE_FAIL", Toast.LENGTH_LONG).show()
                }
                Result.RESULT_CODE_PENDING -> {
                    //支付中
                    Toast.makeText(this@MainActivity, "RESULT_CODE_PENDING", Toast.LENGTH_LONG).show()
                }
                Result.RESULT_CODE_CANCEL -> {
                    //取消支付
                    Toast.makeText(this@MainActivity, "RESULT_CODE_CANCEL", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
