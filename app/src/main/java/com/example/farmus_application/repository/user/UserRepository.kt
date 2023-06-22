package com.example.farmus_application.repository.user

import com.example.farmus_application.ServiceLocator
import com.example.farmus_application.model.user.LoginReq
import com.example.farmus_application.model.user.LoginRes
import com.example.farmus_application.model.user.SignUpReq
import com.example.farmus_application.model.user.SignUpRes
import com.example.farmus_application.network.UserApiClient

class UserRepository(
    private val userApiClient: UserApiClient = ServiceLocator.userApiClient
): UserDataSource {

    override suspend fun postUserSignup(params: SignUpReq): SignUpRes {
        return userApiClient.postSignUp(params = params)
    }

    override suspend fun postUserLogin(params: LoginReq): LoginRes {
        return userApiClient.postLogin(params = params)
    }


}