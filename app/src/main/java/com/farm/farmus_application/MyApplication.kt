package com.farm.farmus_application

import android.app.Application
import android.content.Context
import com.farm.farmus_application.repository.UserPrefsStorage
import com.kakao.sdk.common.KakaoSdk

class MyApplication : Application() {

    init{
        instance = this
    }

    companion object {
        lateinit var instance: MyApplication

        fun getApplicationContext() : Context {
            return instance.applicationContext
        }
    }
    // NATIVE_APP_KEY : 92bf853f239ace67c04e56eece5f31ba
    override fun onCreate() {
        super.onCreate()
        UserPrefsStorage.init(this)
        KakaoSdk.init(this, "92bf853f239ace67c04e56eece5f31ba")
    }
}