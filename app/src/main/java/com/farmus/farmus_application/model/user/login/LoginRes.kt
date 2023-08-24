package com.farmus.farmus_application.model.user.login

import com.google.gson.annotations.SerializedName

data class LoginRes(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: LoginResult?
)