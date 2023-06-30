package com.example.farmus_application.network

import com.example.farmus_application.model.mypage.*
import com.example.farmus_application.model.user.*
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.Query

interface MyPageApiClient {
    @PATCH("/mypage/editInfo/nickname?{email}")
    suspend fun patchEditInfoNickname(
        @Query("email") email: String,
        @Body params : EditInfoNicknameReq
        ): Response<MyPageRes>

    @PATCH("/mypage/editInfo/name?{email}")
    suspend fun patchEditInfoName(
        @Query("email") email: String,
        @Body params : EditInfoNameReq): Response<MyPageRes>

    @PATCH("/mypage/editInfo/password?{email}")
    suspend fun patchEditInfoPassword(
        @Query("email") email: String,
        @Body params : EditInfoPasswordReq): Response<MyPageRes>

    @PATCH("/mypage/eidtInfo/phoneNumber?{email}")
    suspend fun patchEditInfoPhoneNumber(
        @Query("email") email: String,
        @Body params : EditInfoPhoneNumberReq): Response<MyPageRes>

    @PATCH("/mypage/eidtInfo/profileImg?{email}")
    suspend fun patchEditInfoProfileImg(
        @Query("email") email: String,
        @Body params : EditInfoProfileImgReq): Response<MyPageRes>

}
