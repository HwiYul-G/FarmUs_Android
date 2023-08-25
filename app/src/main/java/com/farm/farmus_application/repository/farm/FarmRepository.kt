package com.farm.farmus_application.repository.farm

import com.farm.farmus_application.ServiceLocator
import com.farm.farmus_application.model.farm.detail.DetailRes
import com.farm.farmus_application.model.farm.list.ListRes
import com.farm.farmus_application.model.farm.myfarm.MyFarmRes
import com.farm.farmus_application.model.farm.phone.PhoneNumberRes
import com.farm.farmus_application.model.farm.postings.PostingsRes
import com.farm.farmus_application.model.farm.register.RegisterRes
import com.farm.farmus_application.model.farm.search.SearchedRes
import com.farm.farmus_application.model.favorite.FavoriteFarmRes
import com.farm.farmus_application.network.FarmApiClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import java.io.File
import java.util.*

class FarmRepository(
    private val farmApiClient: FarmApiClient = ServiceLocator.farmApiClient
) : FarmDataSourceInterface {

    override suspend fun getFarmList(email : String): Response<ListRes> {
        return farmApiClient.getFarmList(email)
    }

    override suspend fun postFarmPostings(
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
    ): Response<PostingsRes> {
        // 텍스트 데이터를 RequestBody로 변환
        val nameRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
        val ownerRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), owner)
        val startDateRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), startDate)
        val endDateRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), endDate)
        val priceRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), price)
        val squareMetersRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), squaredMeters)
        val locationBigRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), locationBig)
        val locationMidRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), locationMid)
        val locationSmallRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), locationSmall)
        val descriptionRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), description)
        val categoryRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), category)
        val tagRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), tag)

        // 이미지 데이터를 MultipartBody.Part로 변환
        val imageParts = mutableListOf<MultipartBody.Part>()
        imageFiles.forEach { imageFile ->
            val mimeType = when(imageFile.extension.toLowerCase(Locale.ROOT)){
                "jpeg","jpg" -> "image/jpeg"
                "png" -> "image/png"
                else -> return@forEach
            }
            val imageRequestBody = RequestBody.create(mimeType.toMediaTypeOrNull(), imageFile)
            val imagePart = MultipartBody.Part.createFormData("file", imageFile.path, imageRequestBody)
            imageParts.add(imagePart)
        }

        // API 호출
        return farmApiClient.postFarmPostings(
            name = nameRequestBody,
            owner = ownerRequestBody,
            startDate = startDateRequestBody,
            endDate = endDateRequestBody,
            price = priceRequestBody,
            squaredMeters = squareMetersRequestBody,
            locationBig = locationBigRequestBody,
            locationMid = locationMidRequestBody,
            locationSmall = locationSmallRequestBody,
            description = descriptionRequestBody,
            category = categoryRequestBody,
            tag = tagRequestBody,
            file = imageParts
        )
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

    override suspend fun getFarmDetail(farmid: Int): Response<DetailRes> {
        return farmApiClient.getFarmDetail(farmid)
    }

    override suspend fun getFarmerPhoneNumber(farmId: Int): Response<PhoneNumberRes> {
        return farmApiClient.getFarmerPhoneNumber(farmId)
    }

    override suspend fun getMyFarm(): Response<MyFarmRes> {
        return farmApiClient.getMyFarm()
    }

    override suspend fun getFavoriteFarmList(email: String): Response<FavoriteFarmRes> {
        return farmApiClient.getFavoriteFarmList(email)
    }
    
}