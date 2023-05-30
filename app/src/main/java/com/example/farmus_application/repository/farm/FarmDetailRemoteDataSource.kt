package com.example.farmus_application.repository.farm

import com.example.farmus_application.network.ApiClient

class FarmDetailRemoteDataSource(private val apiClient: ApiClient): FarmDetailDataSource {

    // apiClient의 함수 결과를 return 하는 FarmDetailDataSource의 suspend 함수를 override

}