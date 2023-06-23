package com.example.farmus_application.network

import com.example.farmus_application.model.user.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApiClient {
    @POST("/user/signup")
    suspend fun postSignUp(@Body params: SignUpReq): SignUpRes

    @POST("/user/login")
    suspend fun postLogin(@Body params: LoginReq): LoginRes

    @POST("/user/signup/verification")
    suspend fun postUserSignupVerification(@Body params: SignUpVerificationReq): SignUpVerificationRes

    @POST
    suspend fun postUserVerification(@Body params: VerificationReq): VerificationRes

    @GET("/user/find-account")
    suspend fun getUserFindAccount(
        @Query("name") name: String,
        @Query("phoneNumber") phoneNumber: String
    ): ResultRes

    @GET("/user/find-password")
    suspend fun getUserFindPassword(@Query("userEmail") userEmail: String): ResultRes

    @PATCH("/user/withdrawal?{userEmail}")
    suspend fun patchUserWithdrawal(@Query("userEmail") userEmail: String): ResultRes

    // 농장 찜하기. 생일 수정 API는 추가 예정
}
