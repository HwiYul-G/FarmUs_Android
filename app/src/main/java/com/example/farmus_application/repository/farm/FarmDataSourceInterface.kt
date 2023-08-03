package com.example.farmus_application.repository.farm

import com.example.farmus_application.model.farm.detail.DetailRes
import com.example.farmus_application.model.farm.list.ListRes
import com.example.farmus_application.model.farm.phone.PhoneNumberRes
import com.example.farmus_application.model.farm.postings.PostingsReq
import com.example.farmus_application.model.farm.postings.PostingsRes
import com.example.farmus_application.model.farm.register.RegisterRes
import com.example.farmus_application.model.farm.search.SearchedRes
import com.example.farmus_application.model.favorite.FavoriteFarmRes
import retrofit2.Response

interface FarmDataSourceInterface {

    suspend fun getFarmList(email : String): Response<ListRes>

    suspend fun postFarmPostings(params: PostingsReq): Response<PostingsRes>

    suspend fun patchFarmRegister(token: String): Response<RegisterRes>

    suspend fun getFarmSearchKeyword(keyword: String): Response<SearchedRes>

    suspend fun getFarmSearchByFilter(locationBig : String, locationMid : String): Response<SearchedRes>

    suspend fun getFarmDetail(farmid: Int): Response<DetailRes>

    suspend fun getFarmerPhoneNumber(farmId: Int): Response<PhoneNumberRes>

    suspend fun getFavoriteFarmList(email: String): Response<FavoriteFarmRes>

}