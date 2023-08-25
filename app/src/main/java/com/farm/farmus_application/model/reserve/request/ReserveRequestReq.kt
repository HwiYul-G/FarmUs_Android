package com.farm.farmus_application.model.reserve.request

import com.google.gson.annotations.SerializedName

data class ReserveRequestReq(
    @SerializedName("email") val email : String,
    @SerializedName("farmid") val farmId : String,
    @SerializedName("startDate") val startDate : String,
    @SerializedName("endDate") val endDate : String,
)
