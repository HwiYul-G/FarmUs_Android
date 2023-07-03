package com.example.farmus_application.repository.reserve

import com.example.farmus_application.ServiceLocator
import com.example.farmus_application.model.reserve.cancel.ReserveCancelRes
import com.example.farmus_application.model.reserve.client_list.ReserveClientListRes
import com.example.farmus_application.model.reserve.farm_list.ReserveFarmListRes
import com.example.farmus_application.model.reserve.request.ReserveRequestReq
import com.example.farmus_application.model.reserve.request.ReserveRequestRes
import com.example.farmus_application.model.reserve.status.ReserveStatusRes
import com.example.farmus_application.network.ReserveApiClient
import retrofit2.Response

class ReserveRepository(
    private val reserveApiClient: ReserveApiClient = ServiceLocator.reserveApiClient
): ReserveDataSourceInterface {
    override suspend fun postReserveRequest(params: ReserveRequestReq): Response<ReserveRequestRes> {
        return reserveApiClient.postReserveRequest(params)
    }

    override suspend fun getReservedFarmList(farmId: String): Response<ReserveFarmListRes> {
        return reserveApiClient.getReservedFarmList(farmId)
    }

    override suspend fun getReserveClientList(email: String): Response<ReserveClientListRes> {
        return reserveApiClient.getReserveClientList(email)
    }

    override suspend fun putReserveCancel(reserveId: String): Response<ReserveCancelRes> {
        return reserveApiClient.putReserveCancel(reserveId)
    }

    override suspend fun putReserveStatus(
        status: ReserveDataSourceInterface.Status,
        reserveId: String
    ): Response<ReserveStatusRes> {
        return reserveApiClient.putReserveStatus(status = status.toString(), reserveId = reserveId)
    }

}