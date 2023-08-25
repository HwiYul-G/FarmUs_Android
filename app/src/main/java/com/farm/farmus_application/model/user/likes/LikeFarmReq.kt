package com.farm.farmus_application.model.user.likes

import com.google.gson.annotations.SerializedName

data class LikeFarmReq(
    @SerializedName("email") val email: String,
    @SerializedName("farmid") val farmid: Int,
)
