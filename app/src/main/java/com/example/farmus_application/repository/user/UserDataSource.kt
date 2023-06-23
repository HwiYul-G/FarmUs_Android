package com.example.farmus_application.repository.user

import com.example.farmus_application.model.user.*

interface UserDataSource {

    // suspend 함수 구현
    suspend fun postUserSignup(params: SignUpReq): SignUpRes

    suspend fun postUserLogin(params: LoginReq): LoginRes

    suspend fun postUserSignupVerification(params : SignUpVerificationReq) : SignUpVerificationRes

    suspend fun postUserVerification(params : VerificationReq) : VerificationRes

    suspend fun getUserFindAccount(params : FindAccountReq) : FindAccountRes

    suspend fun getUserFindPassword(params : FindPasswordReq) : FindPasswordRes

    // userEmail을 검색해서 회원 탈퇴
    // suspend fun patchUserWithdrawal(params : WithdrawalReq) : ResResult

    // 농장 id를 입력받아서 해당 id를 post해야함
    // suspend fun postUserStarFarmid(params : FarmidReq) : FarmidRes

    // user의 생일을 입력받아서 수정하는 함수
    // suspend fun postUserBirth(params : BirthReq) : BirthRes

}