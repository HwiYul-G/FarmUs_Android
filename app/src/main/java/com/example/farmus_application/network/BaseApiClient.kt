package com.example.farmus_application.network

import com.example.farmus_application.repository.UserPrefsStorage
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface BaseApiClient {

    companion object {
        private const val baseUrl = "http://118.67.135.247:3000/"
        private val jwtToken = UserPrefsStorage.accessToken ?: ""

        fun create(): Retrofit {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(AppInterceptor())
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        class AppInterceptor: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest = chain.request().newBuilder()
                    .addHeader("token", jwtToken)
                    return chain.proceed(newRequest.build())
            }
        }
    }

}