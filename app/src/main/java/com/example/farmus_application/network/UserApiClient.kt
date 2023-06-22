package com.example.farmus_application.network

import com.example.farmus_application.model.user.LoginReq
import com.example.farmus_application.model.user.LoginRes
import com.example.farmus_application.model.user.SignUpReq
import com.example.farmus_application.model.user.SignUpRes
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiClient {
    @POST("/user/signup")
    suspend fun postSignUp(@Body params: SignUpReq): SignUpRes

    @POST("/user/login")
    suspend fun postLogin(@Body params: LoginReq): LoginRes
}
