package com.farm.farmus_application.data.reserve.repository

import com.farm.farmus_application.data.reserve.remote.ReserveApi
import com.farm.farmus_application.domain.reserve.ReserveRepository
import javax.inject.Inject

class ReserveRepositoryImpl @Inject constructor(private val reserveApi: ReserveApi) : ReserveRepository {

}