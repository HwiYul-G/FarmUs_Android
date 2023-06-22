package com.example.farmus_application.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

interface BaseApiClient {

    companion object {
        private const val baseUrl = "http://101.101.218.107:3000/"

        fun create(): Retrofit {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(Json{ ignoreUnknownKeys = true }
                    .asConverterFactory("application/json".toMediaType()))
                .client(client)
                .build()
        }
    }

}