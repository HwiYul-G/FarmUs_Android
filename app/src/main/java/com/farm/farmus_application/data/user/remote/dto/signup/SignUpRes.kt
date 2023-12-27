package com.farm.farmus_application.data.user.remote.dto.signup

import com.google.gson.annotations.SerializedName

data class SignUpRes(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: SignUpResult
)

data class SignUpResult(
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String
)