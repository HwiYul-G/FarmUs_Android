package com.farm.farmus_application.domain.user.usecase

import com.farm.farmus_application.data.user.remote.dto.verification.VerificationReq
import com.farm.farmus_application.domain.user.UserRepository
import javax.inject.Inject

class VerificationUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(verificationReq: VerificationReq) =
        userRepository.verification(verificationReq)

}