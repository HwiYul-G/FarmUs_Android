package com.farm.farmus_application.data.reserve.remote.dto.status

import com.google.gson.annotations.SerializedName

data class ReserveStatusRes(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Integer,
    @SerializedName("message") val message : String
)
