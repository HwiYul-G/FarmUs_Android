package com.farm.farmus_application.network

import android.util.Log
import com.farm.farmus_application.repository.UserPrefsStorage
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

//interface BaseApiClient {
//
//    companion object {
//        private const val baseUrl = "https://118.67.135.247/"
//        private val jwtToken = UserPrefsStorage.accessToken ?: ""
//
//        fun create(): Retrofit {
//            val logger = HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BASIC
//            }
//
//            val client = unsafeOkHttpClient()
//                .addInterceptor(logger)
//                .addInterceptor(AppInterceptor())
//                .build()
//
//            return Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//        }
//
//        private fun unsafeOkHttpClient(): OkHttpClient.Builder {
//            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
//                override fun checkClientTrusted(
//                    chain: Array<out java.security.cert.X509Certificate>?,
//                    authType: String?
//                ) {
//
//                }
//
//                override fun checkServerTrusted(
//                    chain: Array<out java.security.cert.X509Certificate>?,
//                    authType: String?
//                ) {
//
//                }
//
//                override fun getAcceptedIssuers(): Array<out java.security.cert.X509Certificate>? {
//                    return arrayOf()
//                }
//            })
//
//            val sslContext = SSLContext.getInstance("SSL")
//            sslContext.init(null, trustAllCerts, SecureRandom())
//
//            val sslSocketFactory = sslContext.socketFactory
//
//
//            val builder = OkHttpClient.Builder()
//            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
//            builder.hostnameVerifier { hostname, session -> true }
//
//            return builder
//        }
//
//        class AppInterceptor : Interceptor {
//            override fun intercept(chain: Interceptor.Chain): Response {
//                Log.d("BASEAPICLIENT", "token : ${jwtToken.toString()}")
//                val newRequest = chain.request().newBuilder()
//                    .addHeader("token", jwtToken.toString())
//                return chain.proceed(newRequest.build())
//            }
//        }
//    }
//
//}