package com.example.farmus_application.repository.user

import com.example.farmus_application.ServiceLocator
import com.example.farmus_application.model.user.findaccount.FindAccountRes
import com.example.farmus_application.model.user.findpassword.FindPasswordRes
import com.example.farmus_application.model.user.login.LoginReq
import com.example.farmus_application.model.user.login.LoginRes
import com.example.farmus_application.model.user.signup.SignUpReq
import com.example.farmus_application.model.user.signup.SignUpRes
import com.example.farmus_application.model.user.signup_verification.SignUpVerificationReq
import com.example.farmus_application.model.user.signup_verification.SignUpVerificationRes
import com.example.farmus_application.model.user.verification.VerificationReq
import com.example.farmus_application.model.user.verification.VerificationRes
import com.example.farmus_application.model.user.withdrawal.WithdrawalRes
import com.example.farmus_application.network.UserApiClient
import retrofit2.Response

class UserRepository(
    private val userApiClient: UserApiClient = ServiceLocator.userApiClient
) {

    suspend fun postUserSignup(params: SignUpReq): Response<SignUpRes> {
        return userApiClient.postSignUp(params = params)
    }

    suspend fun postUserLogin(params: LoginReq): Response<LoginRes> {
        return userApiClient.postLogin(params = params)
    }

    suspend fun postUserSignupVerification(params : SignUpVerificationReq): Response<SignUpVerificationRes> {
        return userApiClient.postUserSignupVerification(params = params)
    }

    suspend fun postUserVerification(params : VerificationReq): Response<VerificationRes> {
        return userApiClient.postUserVerification(params = params)
    }

    // == get과 patch에 대한
    suspend fun getUserFindAccount(name : String, phoneNumber : String): Response<FindAccountRes> {
        return userApiClient.getUserFindAccount(name = name, phoneNumber = phoneNumber)
    }

    suspend fun getUserFindPassword(userEmail : String): Response<FindPasswordRes> {
        return userApiClient.getUserFindPassword(userEmail = userEmail)
    }

    suspend fun patchUserWithdrawal(userEmail: String): Response<WithdrawalRes>{
        return userApiClient.patchUserWithdrawal(userEmail = userEmail)
    }

//    suspend fun postUserStarFarmid(params : StarFarmidReq): StarFarmidRes {
//        return userApiClient.postUserStarFarmid(params = params)
//    }


//    suspend fun postUserBirth(params : BirthReq): BirthRes {
//        return userApiClient.postUserBirth(params = params)
//    }


}