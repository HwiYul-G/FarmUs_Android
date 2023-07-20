package com.example.farmus_application.repository.myPage

import com.example.farmus_application.model.mypage.*
import retrofit2.Response

interface MyPageDataSourceInterface {
    suspend fun patchEditInfoNickname(
        email: String,
        params: EditInfoNicknameReq
    ): Response<MyPageRes>

    suspend fun patchEditInfoName(email: String, params: EditInfoNameReq): Response<MyPageRes>
    suspend fun patchEditInfoPassword(
        email: String,
        params: EditInfoPasswordReq
    ): Response<MyPagePasswordRes>

    suspend fun patchEditInfoPhoneNumber(
        email: String,
        params: EditInfoPhoneNumberReq
    ): Response<MyPagePhoneNumberRes>

    suspend fun patchEditInfoProfileImg(
        email: String,
        params: EditInfoProfileImgReq
    ): Response<MyPageRes>
}