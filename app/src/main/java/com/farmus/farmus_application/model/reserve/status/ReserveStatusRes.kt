package com.farmus.farmus_application.model.reserve.status

import com.google.gson.annotations.SerializedName

data class ReserveStatusRes(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Integer,
    @SerializedName("message") val message : String
)
