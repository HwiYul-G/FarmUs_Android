package com.example.farmus_application

import com.example.farmus_application.network.*
import retrofit2.Retrofit
import retrofit2.create

object ServiceLocator {

    private val baseApiClient: Retrofit by lazy {
        BaseApiClient.create()
    }

    val userApiClient: UserApiClient by lazy {
        baseApiClient.create(UserApiClient::class.java)
    }

    val farmApiClient: FarmApiClient by lazy {
        baseApiClient.create(FarmApiClient::class.java)
    }

    val myPageApiClient : MyPageApiClient by lazy{
        baseApiClient.create(MyPageApiClient::class.java)
    }

    val reserveApiClient: ReserveApiClient by lazy{
        baseApiClient.create(ReserveApiClient::class.java)
    }

}