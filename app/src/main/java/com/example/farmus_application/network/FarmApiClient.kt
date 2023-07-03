package com.example.farmus_application.network

import com.example.farmus_application.model.farm.list.ListRes
import com.example.farmus_application.model.farm.postings.PostingsReq
import com.example.farmus_application.model.farm.postings.PostingsRes
import com.example.farmus_application.model.farm.register.RegisterRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface FarmApiClient {
    @GET("/farm/list")
    suspend fun getFarmList(): Response<ListRes>

    @POST("/farm/postings")
    suspend fun postFarmPostings(@Body params: PostingsReq): Response<PostingsRes>

    @PATCH("/farm/register")
    suspend fun patchFarmRegister(): Response<RegisterRes>
}