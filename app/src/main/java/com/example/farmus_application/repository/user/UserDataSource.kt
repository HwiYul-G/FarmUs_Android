package com.example.farmus_application.repository.user

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
import retrofit2.Response

interface UserDataSource {

    // suspend 함수 구현
    suspend fun postUserSignup(params: SignUpReq): Response<SignUpRes>

    suspend fun postUserLogin(params: LoginReq): Response<LoginRes>

    suspend fun postUserSignupVerification(params : SignUpVerificationReq) : Response<SignUpVerificationRes>

    suspend fun postUserVerification(params : VerificationReq) : Response<VerificationRes>

    suspend fun getUserFindAccount(name : String, phoneNumber : String) : Response<FindAccountRes>

    suspend fun getUserFindPassword(userEmail : String) : Response<FindPasswordRes>

     suspend fun patchUserWithdrawal(userEmail: String) : Response<WithdrawalRes>

    // 농장 id를 입력받아서 해당 id를 post해야함
    // suspend fun postUserStarFarmid(params : FarmidReq) : FarmidRes

    // user의 생일을 입력받아서 수정하는 함수
    // suspend fun postUserBirth(params : BirthReq) : BirthRes

}