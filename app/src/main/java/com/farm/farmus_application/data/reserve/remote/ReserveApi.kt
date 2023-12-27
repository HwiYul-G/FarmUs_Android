package com.farm.farmus_application.data.reserve.remote

import com.farm.farmus_application.data.reserve.remote.dto.cancel.ReserveCancelRes
import com.farm.farmus_application.data.reserve.remote.dto.client_list.ReserveClientListRes
import com.farm.farmus_application.data.reserve.remote.dto.farm_list.ReserveFarmListRes
import com.farm.farmus_application.data.reserve.remote.dto.request.ReserveRequestReq
import com.farm.farmus_application.data.reserve.remote.dto.request.ReserveRequestRes
import com.farm.farmus_application.data.reserve.remote.dto.reserve_list.ReserveListRes
import com.farm.farmus_application.data.reserve.remote.dto.status.ReserveStatusRes
import com.farm.farmus_application.data.reserve.remote.dto.unbookable.ReserveUnBookableRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReserveApi {
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

    @GET("/reserve/current/list")
    suspend fun getCurrentList(): Response<ReserveListRes>

    @GET("/reserve/past/list/")
    suspend fun getPastList(): Response<ReserveListRes>
}