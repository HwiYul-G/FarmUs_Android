package com.example.farmus_application.ui.login

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class KakaoLogin : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "92bf853f239ace67c04e56eece5f31ba")
    }
}