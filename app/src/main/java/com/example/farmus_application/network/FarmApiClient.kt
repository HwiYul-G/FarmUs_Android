package com.example.farmus_application.network

import com.example.farmus_application.model.farm.list.ListRes
import com.example.farmus_application.model.farm.postings.PostingsReq
import com.example.farmus_application.model.farm.postings.PostingsRes
import com.example.farmus_application.model.farm.register.RegisterRes
import com.example.farmus_application.model.farm.search.SearchedRes
import retrofit2.Response
import retrofit2.http.*

interface FarmApiClient {
    @GET("/farm/list/{email}")
    suspend fun getFarmList(@Path(value="email") email : String): Response<ListRes>

    @POST("/farm/postings")
    suspend fun postFarmPostings(@Body params: PostingsReq): Response<PostingsRes>

    @PATCH("/farm/register")
    suspend fun patchFarmRegister(): Response<RegisterRes>

    @GET("/farm/search")
    suspend fun getFarmSearchKeyword(@Query("keyword") keyword: String) : Response<SearchedRes>
}