package com.example.farmus_application.repository.myPage

import com.example.farmus_application.ServiceLocator
import com.example.farmus_application.model.mypage.*
import com.example.farmus_application.network.MyPageApiClient
import retrofit2.Response

class MyPageRepository(
    private val myPageApiClient: MyPageApiClient = ServiceLocator.myPageApiClient
) : MyPageDataSourceInterface {
    override suspend fun patchEditInfoNickname(
        email: String,
        params: EditInfoNicknameReq
    ): Response<MyPageRes> {
        return myPageApiClient.patchEditInfoNickname(email, params)
    }

    override suspend fun patchEditInfoName(
        email: String,
        params: EditInfoNameReq
    ): Response<MyPageRes> {
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
        params: EditInfoProfileImgReq
    ): Response<MyPageRes> {
        return myPageApiClient.patchEditInfoProfileImg(email, params)
    }

}