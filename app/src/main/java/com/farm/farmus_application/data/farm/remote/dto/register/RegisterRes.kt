package com.farm.farmus_application.data.farm.remote.dto.register

import com.google.gson.annotations.SerializedName

data class RegisterRes(
    @SerializedName("result") val result: Boolean,
    @SerializedName("accesstoken") val accessToken: String
)
