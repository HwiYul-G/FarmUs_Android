package com.farm.farmus_application.domain.mypage.usecase

import com.farm.farmus_application.data.mypage.remote.dto.EditInfoPhoneNumberReq
import com.farm.farmus_application.domain.mypage.MyPageRepository
import javax.inject.Inject

class UpdatePhoneNumber @Inject constructor(private val myPageRepository: MyPageRepository) {
    suspend operator fun invoke(email: String, editInfoPhoneNumberReq: EditInfoPhoneNumberReq) =
        myPageRepository.patchEditInfoPhoneNumber(email, editInfoPhoneNumberReq)
}