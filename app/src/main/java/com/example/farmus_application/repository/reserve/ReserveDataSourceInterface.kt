package com.example.farmus_application.repository.reserve

import com.example.farmus_application.model.reserve.cancel.ReserveCancelRes
import com.example.farmus_application.model.reserve.client_list.ReserveClientListRes
import com.example.farmus_application.model.reserve.farm_list.ReserveFarmListRes
import com.example.farmus_application.model.reserve.request.ReserveRequestReq
import com.example.farmus_application.model.reserve.request.ReserveRequestRes
import com.example.farmus_application.model.reserve.reserve_list.ReserveListRes
import com.example.farmus_application.model.reserve.status.ReserveStatusRes
import com.example.farmus_application.model.reserve.unbookable.ReserveUnBookableRes
import retrofit2.Response

interface ReserveDataSourceInterface {
    enum class Status{
        HOLD, ACCEPT, DENIED
    }

    suspend fun postReserveRequest(params: ReserveRequestReq): Response<ReserveRequestRes>

    suspend fun getReservedFarmList(farmId: String): Response<ReserveFarmListRes>

    suspend fun getReserveClientList(email: String): Response<ReserveClientListRes>

    suspend fun putReserveCancel(reserveId: String): Response<ReserveCancelRes>

    suspend fun putReserveStatus(status: Status, reserveId: String): Response<ReserveStatusRes>

    suspend fun getReserveUnBookable(farmId: String): Response<ReserveUnBookableRes>

    suspend fun getCurrentList(email: String): Response<ReserveListRes>

    suspend fun getPastList(email: String): Response<ReserveListRes>

}
