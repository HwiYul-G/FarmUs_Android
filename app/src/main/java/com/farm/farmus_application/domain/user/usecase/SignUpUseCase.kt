package com.farm.farmus_application.domain.user.usecase

import com.farm.farmus_application.data.user.remote.dto.signup.SignUpReq
import com.farm.farmus_application.domain.user.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(signUpRequest: SignUpReq) = userRepository.signUp(signUpRequest);

}