package com.farm.farmus_application.data.reserve.remote.dto.request

import com.google.gson.annotations.SerializedName

data class ReserveRequestReq(
    @SerializedName("email") val email : String,
    @SerializedName("farmid") val farmId : String,
    @SerializedName("startDate") val startDate : String,
    @SerializedName("endDate") val endDate : String,
)
