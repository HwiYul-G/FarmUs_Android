package com.farm.farmus_application.data.farm.remote.dto.phone

import com.google.gson.annotations.SerializedName

data class PhoneNumberRes(
    @SerializedName("PhoneNumber") val PhoneNumber: String,
    @SerializedName("result") val result: Boolean
)