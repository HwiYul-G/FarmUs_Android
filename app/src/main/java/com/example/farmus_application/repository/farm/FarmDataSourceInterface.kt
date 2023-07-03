package com.example.farmus_application.repository.farm

import com.example.farmus_application.model.farm.list.ListRes
import com.example.farmus_application.model.farm.postings.PostingsReq
import com.example.farmus_application.model.farm.postings.PostingsRes
import com.example.farmus_application.model.farm.register.RegisterRes
import retrofit2.Response

interface FarmDataSourceInterface {

    suspend fun getFarmList(): Response<ListRes>

    suspend fun postFarmPostings(params: PostingsReq): Response<PostingsRes>

    suspend fun patchFarmRegister(): Response<RegisterRes>
}