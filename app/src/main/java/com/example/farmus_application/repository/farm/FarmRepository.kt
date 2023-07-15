package com.example.farmus_application.repository.farm

import com.example.farmus_application.ServiceLocator
import com.example.farmus_application.model.farm.list.ListRes
import com.example.farmus_application.model.farm.postings.PostingsReq
import com.example.farmus_application.model.farm.postings.PostingsRes
import com.example.farmus_application.model.farm.register.RegisterRes
import com.example.farmus_application.model.farm.search.SearchedRes
import com.example.farmus_application.network.FarmApiClient
import retrofit2.Response

class FarmRepository(
    private val farmApiClient: FarmApiClient = ServiceLocator.farmApiClient
) : FarmDataSourceInterface {

    override suspend fun getFarmList(email : String): Response<ListRes> {
        return farmApiClient.getFarmList(email)
    }

    override suspend fun postFarmPostings(params: PostingsReq): Response<PostingsRes> {
        return farmApiClient.postFarmPostings(params)
    }

    override suspend fun patchFarmRegister(): Response<RegisterRes> {
        return farmApiClient.patchFarmRegister()
    }

    override suspend fun getFarmSearchKeyword(keyword: String): Response<SearchedRes> {
        return farmApiClient.getFarmSearchKeyword(keyword)
    }

    override suspend fun getFarmSearchByFilter(locationBig : String, locationMid : String): Response<SearchedRes> {
        return farmApiClient.getFarmSearchByFilter(locationBig, locationMid)
    }
}