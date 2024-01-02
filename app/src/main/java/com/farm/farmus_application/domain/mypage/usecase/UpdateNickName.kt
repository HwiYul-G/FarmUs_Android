package com.farm.farmus_application.domain.mypage.usecase

import com.farm.farmus_application.data.mypage.remote.dto.EditInfoNicknameReq
import com.farm.farmus_application.domain.mypage.MyPageRepository
import javax.inject.Inject

class UpdateNickName @Inject constructor(private val myPageRepository: MyPageRepository) {
    suspend operator fun invoke(email: String, editInfoNickNameRes: EditInfoNicknameReq) =
        myPageRepository.patchEditInfoNickname(email, editInfoNickNameRes)
}