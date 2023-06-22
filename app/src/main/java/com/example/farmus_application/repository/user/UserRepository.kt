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

    override suspend fun postUserSignupVerification(phoneNumber: String): String {
        return userApiClient.postUserSignupVerification(phoneNumber = phoneNumber)
    }

    override suspend fun postUserVerification(verificationNumber: String): Boolean {
        return userApiClient.postUserVerification(verificationNumber = verificationNumber)
    }

    override suspend fun getUserFindAccount(name: String, phoneNumber: String): String {
        return userApiClient.getUserFindAccount(name = name, phoneNumber = phoneNumber)
    }

    override suspend fun getUserFindPassword(email: String): String {
        return userApiClient.getUserFindPassword(email = email)
    }

    override suspend fun patchUserWithdrawal(email: String): Boolean {
        return userApiClient.patchUserWithdrawal(email = email)
    }

    override suspend fun postUserStarFarmid(farmid: String): String {
        return userApiClient.postUserStarFarmid(farmid = farmid)
    }


}