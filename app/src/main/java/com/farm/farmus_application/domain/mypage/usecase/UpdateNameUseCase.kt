package com.farm.farmus_application.domain.mypage.usecase

import com.farm.farmus_application.data.mypage.remote.dto.EditInfoNameReq
import com.farm.farmus_application.domain.mypage.MyPageRepository
import javax.inject.Inject

class UpdateNameUseCase @Inject constructor(private val myPageRepository: MyPageRepository) {
    suspend operator fun invoke(email: String, editInfoNameRequest: EditInfoNameReq) =
        myPageRepository.patchEditInfoName(email, editInfoNameRequest)
}