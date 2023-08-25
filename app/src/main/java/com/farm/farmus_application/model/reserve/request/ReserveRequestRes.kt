package com.farm.farmus_application.model.reserve.request

import com.google.gson.annotations.SerializedName

data class ReserveRequestRes(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Integer,
    @SerializedName("message") val message : String
)
