package com.example.farmus_application.model.user.login

import com.google.gson.annotations.SerializedName

data class LoginResult(
    @SerializedName("accesstoken") val accesstoken: String,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("nickName") val nickName: String,
    @SerializedName("role") val role: String,
    @SerializedName("status") val status: Boolean
)