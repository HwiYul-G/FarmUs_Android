package com.example.farmus_application.model.user.signup

import com.google.gson.annotations.SerializedName

data class SignUpResult(
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String
)