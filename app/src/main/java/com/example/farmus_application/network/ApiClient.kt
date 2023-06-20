package com.example.farmus_application.network

import com.example.farmus_application.model.FarmDetail
import com.example.farmus_application.model.login.SignUpReq
import com.example.farmus_application.model.login.SignUpRes
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import kotlin.math.log

interface ApiClient {

    // suspend 함수 작성
    @POST("/user/signup")
    suspend fun postSignUp(@Body params: SignUpReq) : SignUpRes

    companion object {
        private const val baseUrl = "http://101.101.218.107:3000/"

        fun create(): ApiClient {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiClient::class.java)
        }
    }

}