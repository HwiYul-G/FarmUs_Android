package com.farm.farmus_application.domain.reserve.usecase

import com.farm.farmus_application.data.reserve.remote.dto.request.ReserveRequestReq
import com.farm.farmus_application.domain.reserve.ReserveRepository
import javax.inject.Inject

class ReserveRequestUseCase @Inject constructor(
    private val reserveRepository: ReserveRepository
) {
    suspend operator fun invoke(reserveRequest: ReserveRequestReq) =
        reserveRepository.postReserveRequest(reserveRequest)
}
