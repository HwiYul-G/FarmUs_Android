package com.example.farmus_application.repository.user

import com.example.farmus_application.ServiceLocator
import com.example.farmus_application.model.user.*
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

    override suspend fun postUserSignupVerification(params : SignUpVerificationReq): SignUpVerificationRes {
        return userApiClient.postUserSignupVerification(params = params)
    }

    override suspend fun postUserVerification(params : VerificationReq): VerificationRes {
        return userApiClient.postUserVerification(verificationNumber = verificationNumber)
    }

    // == get과 patch에 대한
    override suspend fun getUserFindAccount(params : FindAccountReq): FindAccountRes {
        return userApiClient.getUserFindAccount(params = params)
    }

    override suspend fun getUserFindPassword(params : FindPasswordReq): FindPasswordRes {
        return userApiClient.getUserFindPassword(params = params)
    }

    // data class 이름 임시로 해놓았습니다.
//    override suspend fun patchUserWithdrawal(params : WithdrawalReq): ResResult{
//        return userApiClient.patchUserWithdrawal(params = params)
//    }

//    override suspend fun postUserStarFarmid(params : StarFarmidReq): StarFarmidRes {
//        return userApiClient.postUserStarFarmid(params = params)
//    }


//    override suspend fun postUserBirth(params : BirthReq): BirthRes {
//        return userApiClient.postUserBirth(params = params)
//    }


}