package com.example.farmus_application.repository.user

import com.example.farmus_application.model.user.LoginReq
import com.example.farmus_application.model.user.LoginRes
import com.example.farmus_application.model.user.SignUpReq
import com.example.farmus_application.model.user.SignUpRes

interface UserDataSource {

    // suspend 함수 구현
    suspend fun postUserSignup(params: SignUpReq): SignUpRes

    suspend fun postUserLogin(params: LoginReq): LoginRes

    // 휴대전화 번호를 입력받아서 그 번호로 인증번호를 보냄(인증결과를 String으로? or Int로)
    suspend fun postUserSignupVerification(phoneNumber : String) : String

    // 인증번호를 입력받아서 true인지 false인지 확인
    suspend fun postUserVerification(verificationNumber : String) : Boolean

    // name과 phoneNumber로 아이디를 찾음
    suspend fun getUserFindAccount(name : String, phoneNumber : String) : String

    // user email로 비밀번호 찾음
    suspend fun getUserFindPassword(email : String) : String

    // userEmail을 검색해서 회원 탈퇴
    suspend fun patchUserWithdrawal(email : String) : Boolean

    // 농장 id를 입력받아서 해당 id를 post해야함
    suspend fun postUserStarFarmid(farmid : String) : String

    // 사용자의 생년월일에 대한 data type이 아직 존재하지 않음
    // suspend fun postUserBirth()

}