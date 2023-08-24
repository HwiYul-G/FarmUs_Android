package com.farmus.farmus_application.model.farm.phone

import com.google.gson.annotations.SerializedName

data class PhoneNumberRes(
    @SerializedName("PhoneNumber") val PhoneNumber: String,
    @SerializedName("result") val result: Boolean
)