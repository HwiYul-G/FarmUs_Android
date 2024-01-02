package com.farm.farmus_application.data.farm.remote



import com.farm.farmus_application.data.farm.remote.dto.detail.DetailRes
import com.farm.farmus_application.data.farm.remote.dto.editinfo.EditinfoRes
import com.farm.farmus_application.data.farm.remote.dto.favorite.FavoriteFarmRes
import com.farm.farmus_application.data.farm.remote.dto.list.ListRes
import com.farm.farmus_application.data.farm.remote.dto.myfarm.MyFarmRes
import com.farm.farmus_application.data.farm.remote.dto.phone.PhoneNumberRes
import com.farm.farmus_application.data.farm.remote.dto.postings.PostingsRes
import com.farm.farmus_application.data.farm.remote.dto.register.RegisterRes
import com.farm.farmus_application.data.farm.remote.dto.search.SearchedRes
import com.farm.farmus_application.data.farm.remote.dto.unavailableDate.DeleteDateRes
import com.farm.farmus_application.data.farm.remote.dto.unavailableDate.RetrieveDateRes
import com.farm.farmus_application.data.farm.remote.dto.unavailableDate.UnavailableDateAdditionReq
import com.farm.farmus_application.data.farm.remote.dto.unavailableDate.UnavailaleDateAdditionRes
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface FarmApi {
    @GET("/farm/list/{email}")
    suspend fun getFarmList(@Path(value = "email") email: String): Response<ListRes>

    @Multipart
    @POST("/farm/postings")
    suspend fun postFarmPostings(
        @Part("name") name: RequestBody,
        @Part("owner") owner: RequestBody,
        @Part("startDate") startDate: RequestBody,
        @Part("endDate") endDate: RequestBody,
        @Part("price") price: RequestBody,
        @Part("squaredMeters") squaredMeters: RequestBody,
        @Part("locationBig") locationBig: RequestBody,
        @Part("locationMid") locationMid: RequestBody,
        @Part("locationSmall") locationSmall: RequestBody,
        @Part("description") description: RequestBody,
        @Part("category") category: RequestBody,
        @Part("tag") tag: RequestBody,
        @Part file: List<MultipartBody.Part> // 사진 파일
    ): Response<PostingsRes>

    @Multipart
    @PATCH("/farm/editinfo")
    suspend fun patchFarmEditinfo(
        @Query("farmId") farmId: Int,
        @Part("farmName") farmName: RequestBody,
        @Part("farmInfo") farmInfo: RequestBody,
        @Part("LocationBig") locationBig: RequestBody,
        @Part("LocationMid") locationMid: RequestBody,
        @Part("LocationSmall") locationSmall: RequestBody,
        @Part("size") size: RequestBody,
        @Part("price") price: RequestBody,
        @Part file: List<MultipartBody.Part>
    ): Response<EditinfoRes>

    @PATCH("/farm/register")
    suspend fun patchFarmRegister(): Response<RegisterRes>


    @GET("/farm/search")
    suspend fun getFarmSearchKeyword(@Query("keyword") keyword: String): Response<SearchedRes>

    @GET("/farm")
    suspend fun getFarmSearchByFilter(
        @Query("locationBig") locationBig: String,
        @Query("locationMid") locationMid: String
    ): Response<SearchedRes>

    @GET("/farm/detail/{farmid}")
    suspend fun getFarmDetail(@Path(value = "farmid") farmid: Int): Response<DetailRes>

    @GET("/farm/farmerPhoneNumber")
    suspend fun getFarmerPhoneNumber(@Query("farmID") farmID: Int): Response<PhoneNumberRes>

    @GET("/farm/likes")
    suspend fun getFavoriteFarmList(@Query("email") email: String): Response<FavoriteFarmRes>

    @GET("/farm/myfarm")
    suspend fun getMyFarm(): Response<MyFarmRes>

    @POST("/farm/unavailableDate")
    suspend fun postUnavailableDate(@Body params : UnavailableDateAdditionReq) : Response<UnavailaleDateAdditionRes>

    @PUT("/farm/unavailableDate/delete/{farmDateID}")
    suspend fun putDeleteUnavailableDate(@Path("farmDateID")farmDateID : Int) : Response<DeleteDateRes>

    @GET("/farm/unavailableDate/{farmId}")
    suspend fun getUnavailableDate(@Path("farmId")farmId : Int) : Response<RetrieveDateRes>
}