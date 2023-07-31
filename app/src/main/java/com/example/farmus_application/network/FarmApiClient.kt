package com.example.farmus_application.network

import com.example.farmus_application.model.farm.detail.DetailRes
import com.example.farmus_application.model.farm.list.ListRes
import com.example.farmus_application.model.farm.phone.PhoneNumberRes
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
    suspend fun patchFarmRegister(
        @Header("token") token: String
    ): Response<RegisterRes>


    @GET("/farm/search")
    suspend fun getFarmSearchKeyword(@Query("keyword") keyword: String) : Response<SearchedRes>

    @GET("/farm")
    suspend fun getFarmSearchByFilter(@Query("locationBig") locationBig : String,
                                      @Query("locationMid") locationMid : String) : Response<SearchedRes>

    @GET("/farm/detail/{farmid}")
    suspend fun getFarmDetail(@Path(value="farmid") farmid : Int): Response<DetailRes>

    @GET("/farm/farmerPhoneNumber")
    suspend fun getFarmerPhoneNumber(@Query("farmID") farmID: Int) : Response<PhoneNumberRes>

}