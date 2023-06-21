package com.example.farmus_application.repository.farm

import com.example.farmus_application.model.FarmDetail
import com.example.farmus_application.network.ApiClient

class FarmDetailRepository(private val apiClient: ApiClient): FarmDetailDataSource {

    // remoteDataSource의 suspend 함수의 결과값을 return 하는 suspend 함수 작성
//    suspend fun getFarmList(): List<FarmDetail> {
//        return remoteDataSource.getFarmList()
//    }
}