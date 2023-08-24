package com.farmus.farmus_application.model.user.verification

import com.google.gson.annotations.SerializedName

data class VerificationRes(
    @SerializedName("code") val code: Int,
    @SerializedName("email") val email: String,
    @SerializedName("message") val message: String,
    @SerializedName("name") val name: String,
    @SerializedName("result") val result: Boolean
)