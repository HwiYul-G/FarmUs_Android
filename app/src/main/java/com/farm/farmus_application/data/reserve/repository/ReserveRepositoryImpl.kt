package com.farm.farmus_application.data.reserve.repository

import com.farm.farmus_application.data.reserve.remote.ReserveApi
import com.farm.farmus_application.data.reserve.remote.dto.cancel.ReserveCancelRes
import com.farm.farmus_application.data.reserve.remote.dto.client_list.ReserveClientListRes
import com.farm.farmus_application.data.reserve.remote.dto.farm_list.ReserveFarmListRes
import com.farm.farmus_application.data.reserve.remote.dto.request.ReserveRequestReq
import com.farm.farmus_application.data.reserve.remote.dto.request.ReserveRequestRes
import com.farm.farmus_application.data.reserve.remote.dto.reserve_list.ReserveListRes
import com.farm.farmus_application.data.reserve.remote.dto.status.ReserveStatusRes
import com.farm.farmus_application.data.reserve.remote.dto.unbookable.ReserveUnBookableRes
import com.farm.farmus_application.domain.BaseResult
import com.farm.farmus_application.domain.reserve.ReserveRepository
import com.farm.farmus_application.domain.reserve.entity.ReserveCancelEntity
import com.farm.farmus_application.domain.reserve.entity.ReserveClientListEntity
import com.farm.farmus_application.domain.reserve.entity.ReserveFarmListEntity
import com.farm.farmus_application.domain.reserve.entity.ReserveListEntity
import com.farm.farmus_application.domain.reserve.entity.ReserveRequestEntity
import com.farm.farmus_application.domain.reserve.entity.ReserveStatusEntity
import com.farm.farmus_application.domain.reserve.entity.ReserveUnBookableEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReserveRepositoryImpl @Inject constructor(private val reserveApi: ReserveApi) : ReserveRepository {
    override suspend fun postReserveRequest(reserveRequest: ReserveRequestReq): Flow<BaseResult<ReserveRequestEntity, ReserveRequestRes>> {
        TODO("Not yet implemented")
    }

    override suspend fun getReservedFarmList(farmId: String): Flow<BaseResult<ReserveFarmListEntity, ReserveFarmListRes>> {
        TODO("Not yet implemented")
    }

    override suspend fun getReserveClientList(email: String): Flow<BaseResult<ReserveClientListEntity, ReserveClientListRes>> {
        TODO("Not yet implemented")
    }

    override suspend fun putReserveCancel(reserveId: String): Flow<BaseResult<ReserveCancelEntity, ReserveCancelRes>> {
        TODO("Not yet implemented")
    }

    override suspend fun putReserveStatus(
        status: String,
        reserveId: String
    ): Flow<BaseResult<ReserveStatusEntity, ReserveStatusRes>> {
        TODO("Not yet implemented")
    }

    override suspend fun getReserveUnBookable(farmId: String): Flow<BaseResult<ReserveUnBookableEntity, ReserveUnBookableRes>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentList(): Flow<BaseResult<ReserveListEntity, ReserveListRes>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPastList(): Flow<BaseResult<ReserveListEntity, ReserveListRes>> {
        TODO("Not yet implemented")
    }

}