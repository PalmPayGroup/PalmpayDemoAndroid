package com.plampay.sdk.demo

import android.app.Application
import com.transsnet.gcd.sdk.CashierDesk

class App : Application() {

    //请自行申请
    private val appId ="appId"
    private val appKey = "appKey"

    override fun onCreate() {
        super.onCreate()

        val builder = CashierDesk.Builder()
        builder.setAppId(appId)
        builder.setAppKey(appKey)

        // 测试环境设置该值，生产环境删除该行代码
        builder.setEnv(CashierDesk.ENV_DEV)
        CashierDesk.init(this, builder)

    }
}