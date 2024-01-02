package com.farm.farmus_application.domain.reserve.usecase

import com.farm.farmus_application.domain.reserve.ReserveRepository
import javax.inject.Inject

class PutReserveStatusUseCase @Inject constructor(
    private val reserveRepository: ReserveRepository
) {
    suspend operator fun invoke(status: String, reserveId: String) =
        reserveRepository.putReserveStatus(status, reserveId);
}