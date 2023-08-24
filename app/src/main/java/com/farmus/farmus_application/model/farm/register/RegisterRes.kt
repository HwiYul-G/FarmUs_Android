package com.farmus.farmus_application.model.farm.register

import com.google.gson.annotations.SerializedName

data class RegisterRes(
    @SerializedName("result") val result: Boolean,
    @SerializedName("accesstoken") val accessToken: String
)
