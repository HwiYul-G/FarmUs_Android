package com.farm.farmus_application.repository.user

import com.farm.farmus_application.model.user.findaccount.FindAccountRes
import com.farm.farmus_application.model.user.findpassword.FindPasswordRes
import com.farm.farmus_application.model.user.likes.DeleteLikeFarmRes
import com.farm.farmus_application.model.user.likes.LikeFarmReq
import com.farm.farmus_application.model.user.likes.LikeFarmRes
import com.farm.farmus_application.model.user.login.LoginReq
import com.farm.farmus_application.model.user.login.LoginRes
import com.farm.farmus_application.model.user.signup.SignUpReq
import com.farm.farmus_application.model.user.signup.SignUpRes
import com.farm.farmus_application.model.user.signup_verification.SignUpVerificationReq
import com.farm.farmus_application.model.user.signup_verification.SignUpVerificationRes
import com.farm.farmus_application.model.user.verification.VerificationReq
import com.farm.farmus_application.model.user.verification.VerificationRes
import com.farm.farmus_application.model.user.withdrawal.WithdrawalRes
import retrofit2.Response

interface UserDataSourceInterface {

    // suspend 함수 구현
    suspend fun postUserSignup(params: SignUpReq): Response<SignUpRes>

    suspend fun postUserLogin(params: LoginReq): Response<LoginRes>

    suspend fun getUserEmailVerification(params: String): Response<LoginRes>

    suspend fun postUserSignupVerification(params: SignUpVerificationReq): Response<SignUpVerificationRes>

    suspend fun postUserVerification(params: VerificationReq): Response<VerificationRes>

    suspend fun getUserFindAccount(name: String, phoneNumber: String): Response<FindAccountRes>

    suspend fun getUserFindPassword(userEmail: String): Response<FindPasswordRes>

    suspend fun patchUserWithdrawal(userEmail: String): Response<WithdrawalRes>

    suspend fun postUserLikeFarm(params: LikeFarmReq): Response<LikeFarmRes>

    suspend fun deleteUserLikeFarm(email : String, farmid : Int): Response<DeleteLikeFarmRes>

    // user의 생일을 입력받아서 수정하는 함수
    // suspend fun postUserBirth(params : BirthReq) : BirthRes

}