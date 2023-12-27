package com.farm.farmus_application.data.user.remote.dto.signup_verification

import com.google.gson.annotations.SerializedName

data class SignUpVerificationRes(
    @SerializedName("result") val result: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)