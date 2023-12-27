package com.farm.farmus_application.domain.user.usecase

import com.farm.farmus_application.data.user.remote.dto.login.LoginRequest
import com.farm.farmus_application.domain.user.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(loginRequest: LoginRequest) = userRepository.login(loginRequest)

}