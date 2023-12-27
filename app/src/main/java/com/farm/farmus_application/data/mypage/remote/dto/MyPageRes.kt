package com.farm.farmus_application.data.mypage.remote.dto

import com.google.gson.annotations.SerializedName


data class MyPageNickNameRes(
    @SerializedName("result") val result: Boolean,
    @SerializedName("accesstoken") val accesstoken : String,
    @SerializedName("photoUrl") val photoUrl : String,
)

data class MyPageNameRes(
    @SerializedName("result") val result: Boolean,
    @SerializedName("accesstoken") val accesstoken : String,
    @SerializedName("photoUrl") val photoUrl : String,
)

data class MyPagePasswordRes(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)

data class MyPagePhoneNumberRes(
    @SerializedName("result") val result: Boolean,
    @SerializedName("accesstoken") val accesstoken : String,
    @SerializedName("photoUrl") val photoUrl : String,
)

// fail 시는 isSuccess가 날라오고 성공시는 result가 날라옴??
data class MyPageProfileImageRes(
    @SerializedName("result") val result: Boolean,
    @SerializedName("accesstoken") val accesstoken : String,
    @SerializedName("photoUrl") val photoUrl : String,
)

