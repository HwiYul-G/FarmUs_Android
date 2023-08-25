package com.farm.farmus_application.model.reserve.cancel

import com.google.gson.annotations.SerializedName

data class ReserveCancelRes(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)
