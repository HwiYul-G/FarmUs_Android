package com.example.farmus_application

import com.example.farmus_application.network.BaseApiClient
import com.example.farmus_application.network.UserApiClient
import retrofit2.Retrofit

object ServiceLocator {

    private val baseApiClient: Retrofit by lazy {
        BaseApiClient.create()
    }

    val userApiClient: UserApiClient by lazy {
        baseApiClient.create(UserApiClient::class.java)
    }

}