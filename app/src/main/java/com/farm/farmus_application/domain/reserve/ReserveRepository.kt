package com.farm.farmus_application.domain.reserve

import com.farm.farmus_application.data.reserve.remote.dto.cancel.ReserveCancelRes
import com.farm.farmus_application.data.reserve.remote.dto.client_list.ReserveClientListRes
import com.farm.farmus_application.data.reserve.remote.dto.farm_list.ReserveFarmListRes
import com.farm.farmus_application.data.reserve.remote.dto.request.ReserveRequestReq
import com.farm.farmus_application.data.reserve.remote.dto.request.ReserveRequestRes
import com.farm.farmus_application.data.reserve.remote.dto.reserve_list.ReserveListRes
import com.farm.farmus_application.data.reserve.remote.dto.status.ReserveStatusRes
import com.farm.farmus_application.data.reserve.remote.dto.unbookable.ReserveUnBookableRes
import com.farm.farmus_application.domain.BaseResult
import com.farm.farmus_application.domain.reserve.entity.ReserveCancelEntity
import com.farm.farmus_application.domain.reserve.entity.ReserveClientListEntity
import com.farm.farmus_application.domain.reserve.entity.ReserveFarmListEntity
import com.farm.farmus_application.domain.reserve.entity.ReserveListEntity
import com.farm.farmus_application.domain.reserve.entity.ReserveRequestEntity
import com.farm.farmus_application.domain.reserve.entity.ReserveStatusEntity
import com.farm.farmus_application.domain.reserve.entity.ReserveUnBookableEntity
import kotlinx.coroutines.flow.Flow

interface ReserveRepository {

    suspend fun postReserveRequest(reserveRequest: ReserveRequestReq): Flow<BaseResult<ReserveRequestEntity, ReserveRequestRes>>

    suspend fun getReservedFarmList(farmId: String): Flow<BaseResult<ReserveFarmListEntity, ReserveFarmListRes>>

    suspend fun getReserveClientList(email: String): Flow<BaseResult<ReserveClientListEntity, ReserveClientListRes>>

    suspend fun putReserveCancel(reserveId: String): Flow<BaseResult<ReserveCancelEntity, ReserveCancelRes>>

    suspend fun putReserveStatus(
        status: String,
        reserveId: String
    ): Flow<BaseResult<ReserveStatusEntity, ReserveStatusRes>>

    suspend fun getReserveUnBookable(farmId: String): Flow<BaseResult<ReserveUnBookableEntity, ReserveUnBookableRes>>
    suspend fun getCurrentList(): Flow<BaseResult<ReserveListEntity, ReserveListRes>>

    suspend fun getPastList(): Flow<BaseResult<ReserveListEntity, ReserveListRes>>

}