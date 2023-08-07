package com.example.farmus_application.repository.user

import com.example.farmus_application.ServiceLocator
import com.example.farmus_application.model.user.findaccount.FindAccountRes
import com.example.farmus_application.model.user.findpassword.FindPasswordRes
import com.example.farmus_application.model.user.likes.DeleteLikeFarmRes
import com.example.farmus_application.model.user.likes.LikeFarmReq
import com.example.farmus_application.model.user.likes.LikeFarmRes
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
) : UserDataSourceInterface {

    override suspend fun postUserSignup(params: SignUpReq): Response<SignUpRes> {
        return userApiClient.postSignUp(params = params)
    }

    override suspend fun postUserLogin(params: LoginReq): Response<LoginRes> {
        return userApiClient.postLogin(params = params)
    }

    override suspend fun getUserEmailVerification(params: String): Response<LoginRes> {
        return userApiClient.getUserEmailVerification(params)
    }

    override suspend fun postUserSignupVerification(params: SignUpVerificationReq): Response<SignUpVerificationRes> {
        return userApiClient.postUserSignupVerification(params = params)
    }

    override suspend fun postUserVerification(params: VerificationReq): Response<VerificationRes> {
        return userApiClient.postUserVerification(params = params)
    }

    // == get과 patch에 대한
    override suspend fun getUserFindAccount(
        name: String,
        phoneNumber: String
    ): Response<FindAccountRes> {
        return userApiClient.getUserFindAccount(name = name, phoneNumber = phoneNumber)
    }

    override suspend fun getUserFindPassword(userEmail: String): Response<FindPasswordRes> {
        return userApiClient.getUserFindPassword(userEmail = userEmail)
    }

    override suspend fun patchUserWithdrawal(userEmail: String): Response<WithdrawalRes> {
        return userApiClient.patchUserWithdrawal(userEmail = userEmail)
    }

    override suspend fun postUserLikeFarm(params: LikeFarmReq): Response<LikeFarmRes> {
        return userApiClient.postUserLikeFarm(params = params)
    }

    override suspend fun deleteUserLikeFarm(email : String, farmid : Int): Response<DeleteLikeFarmRes> {
        return userApiClient.deleteUserLikeFarm(email, farmid)
    }



//    suspend fun postUserBirth(params : BirthReq): BirthRes {
//        return userApiClient.postUserBirth(params = params)
//    }


}