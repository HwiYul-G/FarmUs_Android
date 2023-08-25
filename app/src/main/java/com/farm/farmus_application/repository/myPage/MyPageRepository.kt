package com.farm.farmus_application.repository.myPage

import com.farm.farmus_application.ServiceLocator
import com.farm.farmus_application.model.mypage.*
import com.farm.farmus_application.network.MyPageApiClient
import okhttp3.MultipartBody
import retrofit2.Response

class MyPageRepository(
    private val myPageApiClient: MyPageApiClient = ServiceLocator.myPageApiClient
) : MyPageDataSourceInterface {
    override suspend fun patchEditInfoNickname(
        email: String,
        params: EditInfoNicknameReq
    ): Response<MyPageNickNameRes> {
        return myPageApiClient.patchEditInfoNickname(email, params)
    }

    override suspend fun patchEditInfoName(
        email: String,
        params: EditInfoNameReq
    ): Response<MyPageNameRes> {
        return myPageApiClient.patchEditInfoName(email, params)
    }

    override suspend fun patchEditInfoPassword(
        email: String,
        params: EditInfoPasswordReq
    ): Response<MyPagePasswordRes> {
        return myPageApiClient.patchEditInfoPassword(email, params)
    }

    override suspend fun patchEditInfoPhoneNumber(
        email: String,
        params: EditInfoPhoneNumberReq
    ): Response<MyPagePhoneNumberRes> {
        return myPageApiClient.patchEditInfoPhoneNumber(email, params)
    }

    override suspend fun patchEditInfoProfileImg(
        email: String,
        file: MultipartBody.Part
    ): Response<MyPageProfileImageRes> {
        return myPageApiClient.patchEditInfoProfileImg(email, file)
    }

}