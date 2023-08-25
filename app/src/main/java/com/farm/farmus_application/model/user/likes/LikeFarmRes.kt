package com.farm.farmus_application.model.user.likes

import com.google.gson.annotations.SerializedName

data class LikeFarmRes(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String,

)
