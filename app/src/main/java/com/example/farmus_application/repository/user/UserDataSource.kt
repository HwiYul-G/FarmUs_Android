package com.example.farmus_application.repository.user

import com.example.farmus_application.model.user.LoginReq
import com.example.farmus_application.model.user.LoginRes
import com.example.farmus_application.model.user.SignUpReq
import com.example.farmus_application.model.user.SignUpRes

interface UserDataSource {

    // suspend 함수 구현
    suspend fun postUserSignup(params: SignUpReq): SignUpRes

    suspend fun postUserLogin(params: LoginReq): LoginRes

}