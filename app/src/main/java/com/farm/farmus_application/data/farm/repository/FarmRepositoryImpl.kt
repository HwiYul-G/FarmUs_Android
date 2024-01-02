package com.farm.farmus_application.data.farm.repository

import com.farm.farmus_application.data.farm.remote.FarmApi
import com.farm.farmus_application.domain.farm.FarmRepository
import javax.inject.Inject

class FarmRepositoryImpl @Inject constructor(private val farmApi: FarmApi) : FarmRepository {

}