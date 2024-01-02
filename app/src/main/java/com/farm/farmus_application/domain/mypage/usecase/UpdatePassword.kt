package com.farm.farmus_application.domain.mypage.usecase

import com.farm.farmus_application.data.mypage.remote.dto.EditInfoPasswordReq
import com.farm.farmus_application.domain.mypage.MyPageRepository
import javax.inject.Inject

class UpdatePassword @Inject constructor(private val myPageRepository: MyPageRepository) {
    suspend operator fun invoke(email: String, editInfoPasswordReq: EditInfoPasswordReq) =
        myPageRepository.patchEditInfoPassword(email, editInfoPasswordReq)
}