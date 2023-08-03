package com.example.farmus_application.model.user.likes

import com.google.gson.annotations.SerializedName

data class DeleteLikeFarmReq(
    @SerializedName("email") val email: String,
    @SerializedName("farmid") val farmid: Int,
)
