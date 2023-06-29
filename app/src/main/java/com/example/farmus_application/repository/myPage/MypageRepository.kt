package com.example.farmus_application.repository.myPage

import com.example.farmus_application.model.mypage.*
import com.example.farmus_application.network.MyPageApiClient

class MypageRepository (
    private val myPageApiClient: MyPageApiClient
){
    suspend fun patchEditInfoNicname(email: String, params : EditInfoNicknameReq)
    = myPageApiClient.patchEditInfoNickname(email, params)

    suspend fun patchEditInfoName(email: String, params : EditInfoNameReq)
    = myPageApiClient.patchEditInfoName(email, params)

    suspend fun patchEditInfoPassword(email: String, params : EditInfoPasswordReq)
    = myPageApiClient.patchEditInfoPassword(email, params)

    suspend fun patchEditInfoPhoneNumber(email: String, params : EditInfoPhoneNumberReq)
    = myPageApiClient.patchEditInfoPhoneNumber(email, params)

    suspend fun patchEditInfoProfileImg(email: String, params : EditInfoProfileImgReq)
    = myPageApiClient.patchEditInfoProfileImg(email, params)

}