package com.farm.farmus_application.domain.user.usecase

import com.farm.farmus_application.data.user.remote.dto.likes.LikeFarmReq
import com.farm.farmus_application.domain.user.UserRepository
import javax.inject.Inject

class LikeFarmUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(likeFarmReq: LikeFarmReq) = userRepository.likeFarm(likeFarmReq)

}