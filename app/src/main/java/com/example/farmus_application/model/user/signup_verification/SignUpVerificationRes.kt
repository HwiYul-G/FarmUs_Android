package com.example.farmus_application.model.user.signup_verification

import com.google.gson.annotations.SerializedName

data class SignUpVerificationRes(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Boolean
)