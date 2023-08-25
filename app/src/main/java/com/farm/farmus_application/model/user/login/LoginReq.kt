package com.farm.farmus_application.model.user.login

import com.google.gson.annotations.SerializedName

data class LoginReq(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)