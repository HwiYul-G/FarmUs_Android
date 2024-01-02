package com.farm.farmus_application.domain.farm

import com.farm.farmus_application.data.farm.remote.dto.detail.DetailRes
import com.farm.farmus_application.data.farm.remote.dto.editinfo.EditinfoRes
import com.farm.farmus_application.data.farm.remote.dto.favorite.FavoriteFarmRes
import com.farm.farmus_application.data.farm.remote.dto.list.ListRes
import com.farm.farmus_application.data.farm.remote.dto.myfarm.MyFarmRes
import com.farm.farmus_application.data.farm.remote.dto.phone.PhoneNumberRes
import com.farm.farmus_application.data.farm.remote.dto.postings.PostingsRes
import com.farm.farmus_application.data.farm.remote.dto.register.RegisterRes
import com.farm.farmus_application.data.farm.remote.dto.search.SearchedRes
import com.farm.farmus_application.data.farm.remote.dto.unavailableDate.DeleteDateRes
import com.farm.farmus_application.data.farm.remote.dto.unavailableDate.RetrieveDateRes
import com.farm.farmus_application.data.farm.remote.dto.unavailableDate.UnavailableDateAdditionReq
import com.farm.farmus_application.data.farm.remote.dto.unavailableDate.UnavailaleDateAdditionRes
import com.farm.farmus_application.domain.BaseResult
import com.farm.farmus_application.domain.farm.entity.DeleteDateEntity
import com.farm.farmus_application.domain.farm.entity.DetailEntity
import com.farm.farmus_application.domain.farm.entity.EditInfoEntity
import com.farm.farmus_application.domain.farm.entity.FarmListEntity
import com.farm.farmus_application.domain.farm.entity.FavoriteFarmEntity
import com.farm.farmus_application.domain.farm.entity.MyFarmEntity
import com.farm.farmus_application.domain.farm.entity.PhoneNumberEntity
import com.farm.farmus_application.domain.farm.entity.PostingsEntity
import com.farm.farmus_application.domain.farm.entity.RegisterEntity
import com.farm.farmus_application.domain.farm.entity.RetrieveDateEntity
import com.farm.farmus_application.domain.farm.entity.SearchedEntity
import com.farm.farmus_application.domain.farm.entity.UnavailableDateAdditionEntity
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody



interface FarmRepository {

    suspend fun getFarmList(email: String): Flow<BaseResult<FarmListEntity, ListRes>>

    suspend fun postFarmPostings(
        name: RequestBody,
        owner: RequestBody,
        startDate: RequestBody,
        endDate: RequestBody,
        price: RequestBody,
        squaredMeters: RequestBody,
        locationBig: RequestBody,
        locationMid: RequestBody,
        locationSmall: RequestBody,
        description: RequestBody,
        category: RequestBody,
        tag: RequestBody,
        file: List<MultipartBody.Part> // 사진 파일
    ): Flow<BaseResult<PostingsEntity, PostingsRes>>

    suspend fun patchFarmEditinfo(
        farmId: Int,
        farmName: RequestBody,
        farmInfo: RequestBody,
        locationBig: RequestBody,
        locationMid: RequestBody,
        locationSmall: RequestBody,
        size: RequestBody,
        price: RequestBody,
        file: List<MultipartBody.Part>
    ): Flow<BaseResult<EditInfoEntity, EditinfoRes>>

    suspend fun patchFarmRegister(): Flow<BaseResult<RegisterEntity, RegisterRes>>

    suspend fun getFarmSearchKeyword(keyword: String): Flow<BaseResult<SearchedEntity, SearchedRes>>

    suspend fun getFarmSearchByFilter(locationBig: String, locationMid: String): Flow<BaseResult<SearchedEntity, SearchedRes>>

    suspend fun getFarmDetail(farmId: Int): Flow<BaseResult<DetailEntity, DetailRes>>

    suspend fun getFarmerPhoneNumber(farmID: Int): Flow<BaseResult<PhoneNumberEntity, PhoneNumberRes>>

    suspend fun getFavoriteFarmList(email: String): Flow<BaseResult<FavoriteFarmEntity, FavoriteFarmRes>>

    suspend fun getMyFarm(): Flow<BaseResult<MyFarmEntity, MyFarmRes>>

    suspend fun postUnavailableDate( unavailableDateAdditionRequest: UnavailableDateAdditionReq): Flow<BaseResult<UnavailableDateAdditionEntity, UnavailaleDateAdditionRes>>

    suspend fun putDeleteUnavailableDate(farmDateID: Int): Flow<BaseResult<DeleteDateEntity, DeleteDateRes>>

    suspend fun getUnavailableDate(farmId: Int): Flow<BaseResult<RetrieveDateEntity, RetrieveDateRes>>

}