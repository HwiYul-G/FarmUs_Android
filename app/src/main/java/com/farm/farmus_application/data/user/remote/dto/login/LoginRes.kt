package com.farm.farmus_application.data.user.remote.dto.login

import com.google.gson.annotations.SerializedName

data class LoginRes(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: LoginResult?
)