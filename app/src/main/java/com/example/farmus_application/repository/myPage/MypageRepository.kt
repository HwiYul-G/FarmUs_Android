package com.example.farmus_application.repository.myPage

import com.example.farmus_application.ServiceLocator
import com.example.farmus_application.model.mypage.*
import com.example.farmus_application.network.MyPageApiClient

class MypageRepository (
    private val myPageApiClient: MyPageApiClient = ServiceLocator.myPageApiClient
) : MypageDataSourceInterface{
    override suspend fun patchEditInfoNicname(email: String, params : EditInfoNicknameReq){
        myPageApiClient.patchEditInfoNickname(email, params)
    }

    override suspend fun patchEditInfoName(email: String, params : EditInfoNameReq){
        myPageApiClient.patchEditInfoName(email, params)
    }

    override suspend fun patchEditInfoPassword(email: String, params : EditInfoPasswordReq){
        myPageApiClient.patchEditInfoPassword(email, params)
    }

    override suspend fun patchEditInfoPhoneNumber(email: String, params : EditInfoPhoneNumberReq) {
        myPageApiClient.patchEditInfoPhoneNumber(email, params)
    }

    override suspend fun patchEditInfoProfileImg(email: String, params : EditInfoProfileImgReq) {
        myPageApiClient.patchEditInfoProfileImg(email, params)
    }

}