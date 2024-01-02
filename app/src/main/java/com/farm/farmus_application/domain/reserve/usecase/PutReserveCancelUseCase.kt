package com.farm.farmus_application.domain.reserve.usecase

import com.farm.farmus_application.domain.reserve.ReserveRepository
import javax.inject.Inject

class PutReserveCancelUseCase @Inject constructor(
    private val reserveRepository: ReserveRepository
) {
    suspend operator fun invoke(reserveId: String) = reserveRepository.putReserveCancel(reserveId);
}