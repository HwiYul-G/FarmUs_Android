package com.farmus.farmus_application.model.user.signup

import com.google.gson.annotations.SerializedName

data class SignUpRes(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: SignUpResult
)