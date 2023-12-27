package com.farm.farmus_application.domain.user.usecase

import com.farm.farmus_application.domain.user.UserRepository
import javax.inject.Inject

class FindAccountUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(name : String, phone : String) = userRepository.findAccount(name, phone)

}