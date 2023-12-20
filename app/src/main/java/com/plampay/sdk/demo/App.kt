package com.plampay.sdk.demo

import android.app.Application
import com.transsnet.gcd.sdk.CashierDesk

class App : Application() {

    //appId and appKey need to be applied by yourself
    private val appId ="appId"
    private val appKey = "appKey"

    override fun onCreate() {
        super.onCreate()

        val builder = CashierDesk.Builder()
        builder.setAppId(appId)
        builder.setAppKey(appKey)

        // Delete this line of code in the production environment
        builder.setEnv(CashierDesk.ENV_DEV)
        CashierDesk.init(this, builder)

    }
}