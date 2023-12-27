package com.farm.farmus_application.domain.user.usecase

import com.farm.farmus_application.domain.user.UserRepository
import javax.inject.Inject

class FindPasswordUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(email : String) = userRepository.findPassword(email)

}