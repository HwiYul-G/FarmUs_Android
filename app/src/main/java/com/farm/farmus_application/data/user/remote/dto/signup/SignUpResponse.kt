package com.farm.farmus_application.data.user.remote.dto.signup

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String
)