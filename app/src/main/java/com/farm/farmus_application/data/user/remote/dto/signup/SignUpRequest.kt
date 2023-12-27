package com.farm.farmus_application.data.user.remote.dto.signup

import com.google.gson.annotations.SerializedName

data class SignUpRequest (
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("nickName") val nickName: String,
    @SerializedName("name") val name: String,
    @SerializedName("role") val role: String
)