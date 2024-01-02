package com.farm.farmus_application.domain.reserve.usecase

import com.farm.farmus_application.domain.reserve.ReserveRepository
import javax.inject.Inject

class GetReservedFarmListUseCase @Inject constructor(private val reserveRepository: ReserveRepository){
    suspend operator fun invoke(farmId : String) = reserveRepository.getReservedFarmList(farmId)

}