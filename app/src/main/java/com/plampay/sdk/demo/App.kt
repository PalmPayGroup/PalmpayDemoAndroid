package com.plampay.sdk.demo

import android.app.Application
import com.transsnet.gcd.sdk.CashierDesk

class App : Application() {

    //appId and appKey need to be applied by yourself
    private val appId ="appId"

    override fun onCreate() {
        super.onCreate()

        val builder = CashierDesk.Builder()
        builder.setAppId(appId)

        // Delete this line of code in the production environment
        builder.setEnv(CashierDesk.ENV_DEV)
        CashierDesk.init(this, builder)

    }
}