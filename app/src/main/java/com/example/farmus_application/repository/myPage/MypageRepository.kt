package com.example.farmus_application.repository.myPage

import com.example.farmus_application.model.mypage.*

interface MypageRepository {
    suspend fun patchEditInfoNicname(email: String, params : EditInfoNicknameReq)
    suspend fun patchEditInfoName(email: String, params : EditInfoNameReq)
    suspend fun patchEditInfoPassword(email: String, params : EditInfoPasswordReq)
    suspend fun patchEditInfoPhoneNumber(email: String, params : EditInfoPhoneNumberReq)
    suspend fun patchEditInfoProfileImg(email: String, params : EditInfoProfileImgReq)
}