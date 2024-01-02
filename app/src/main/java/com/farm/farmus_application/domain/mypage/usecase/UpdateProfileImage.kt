package com.farm.farmus_application.domain.mypage.usecase

import com.farm.farmus_application.domain.mypage.MyPageRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class UpdateProfileImage @Inject constructor(private val myPageRepository: MyPageRepository) {
    suspend operator fun invoke(email: String, file: MultipartBody.Part) =
        myPageRepository.patchEditInfoProfileImg(email, file)
}