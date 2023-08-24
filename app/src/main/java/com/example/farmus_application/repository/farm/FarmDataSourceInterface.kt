package com.example.farmus_application.repository.farm

import com.example.farmus_application.model.farm.detail.DetailRes
import com.example.farmus_application.model.farm.list.ListRes
import com.example.farmus_application.model.farm.myfarm.MyFarmRes
import com.example.farmus_application.model.farm.phone.PhoneNumberRes
import com.example.farmus_application.model.farm.postings.PostingsRes
import com.example.farmus_application.model.farm.register.RegisterRes
import com.example.farmus_application.model.farm.search.SearchedRes
import com.example.farmus_application.model.favorite.FavoriteFarmRes
import retrofit2.Response
import java.io.File

interface FarmDataSourceInterface {

    suspend fun getFarmList(email : String): Response<ListRes>

    suspend fun postFarmPostings(
        name: String,
        owner: String,
        startDate: String,
        endDate: String,
        price: String,
        squaredMeters: String,
        locationBig: String,
        locationMid: String,
        locationSmall: String,
        description: String,
        category: String,
        tag: String,
        imageFiles: List<File>
    ): Response<PostingsRes>

    suspend fun patchFarmRegister(): Response<RegisterRes>

    suspend fun getFarmSearchKeyword(keyword: String): Response<SearchedRes>

    suspend fun getFarmSearchByFilter(locationBig : String, locationMid : String): Response<SearchedRes>

    suspend fun getFarmDetail(farmid: Int): Response<DetailRes>

    suspend fun getFarmerPhoneNumber(farmId: Int): Response<PhoneNumberRes>
  
    suspend fun getMyFarm(): Response<MyFarmRes>
  
    suspend fun getFavoriteFarmList(email: String): Response<FavoriteFarmRes>

}