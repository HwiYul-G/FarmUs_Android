package com.farm.farmus_application.model.user.signup

import com.google.gson.annotations.SerializedName

data class SignUpReq (
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("nickName") val nickName: String,
    @SerializedName("password") val password: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("role") val role: String
)