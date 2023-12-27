package com.farm.farmus_application.data.user.remote.dto.findpassword

import com.google.gson.annotations.SerializedName

data class FindPasswordRes(
    @SerializedName("result") val result: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)