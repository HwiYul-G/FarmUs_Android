package com.farm.farmus_application.domain.user.usecase

import com.farm.farmus_application.data.user.remote.dto.signup_verification.SignUpVerificationReq
import com.farm.farmus_application.domain.user.UserRepository
import javax.inject.Inject

class SignUpVerificationUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(signUpVerificationRequest: SignUpVerificationReq) =
        userRepository.signUpVerification(signUpVerificationRequest)

}