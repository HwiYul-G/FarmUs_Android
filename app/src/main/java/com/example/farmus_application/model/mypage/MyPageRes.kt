package com.example.farmus_application.model.mypage

import com.google.gson.annotations.SerializedName

// backend에서 결과 true, false 날라오는 값의 이름이 다름
data class MyPageRes(
    @SerializedName("result") val result: Boolean, // 닉네임 수정, 이름 수정
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)

data class MyPagePasswordRes(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)

data class MyPagePhoneNumberRes(
    @SerializedName("result") val result: Boolean,
)

data class MyPageProfileImageRes(
    @SerializedName("result") val result: Boolean,
)

