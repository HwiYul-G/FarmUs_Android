package com.example.farmus_application.model.user.verification

import com.google.gson.annotations.SerializedName

data class VerificationReq(
//    @SerializedName("name") val name: String?,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("usercode") val usercode: String
)