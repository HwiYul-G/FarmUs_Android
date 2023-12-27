package com.farm.farmus_application.data.reserve.remote.dto.cancel

import com.google.gson.annotations.SerializedName

data class ReserveCancelRes(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)
