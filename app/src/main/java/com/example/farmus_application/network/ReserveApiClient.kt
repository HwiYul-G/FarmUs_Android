package com.example.farmus_application.network

import com.example.farmus_application.model.reserve.cancel.ReserveCancelRes
import com.example.farmus_application.model.reserve.client_list.ReserveClientListRes
import com.example.farmus_application.model.reserve.farm_list.ReserveFarmListRes
import com.example.farmus_application.model.reserve.request.ReserveRequestReq
import com.example.farmus_application.model.reserve.request.ReserveRequestRes
import com.example.farmus_application.model.reserve.status.ReserveStatusRes
import com.example.farmus_application.model.unbookable.ReserveUnBookableRes
import com.example.farmus_application.model.unbookable.UnBookableResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReserveApiClient {
    @POST("/reserve/request")
    suspend fun postReserveRequest(@Body params: ReserveRequestReq): Response<ReserveRequestRes>

    @GET("/reserve/farm/list/{farmid}")
    suspend fun getReservedFarmList(@Path("farmid")farmId : String): Response<ReserveFarmListRes>

    @GET("/reserve/client/list/{email}")
    suspend fun getReserveClientList(@Path("email")email : String): Response<ReserveClientListRes>

    @PUT("/reserve/cancel/{reserveid}")
    suspend fun putReserveCancel(@Path("reserveid")reserveId : String): Response<ReserveCancelRes>

    @PUT("/reserve/{status}/{reserveid}")
    suspend fun putReserveStatus(
        @Path("status")status : String,
        @Path("reserveid")reserveId : String
    ): Response<ReserveStatusRes>

    @GET("/reserve/unbookable/{farmid}")
    suspend fun getReserveUnBookable(@Path("farmid") farmId: String): Response<ReserveUnBookableRes>
}