package com.farm.farmus_application.data.reserve.remote.dto.client_list

import com.google.gson.annotations.SerializedName

data class ReserveClientListResult(
    @SerializedName("FarmID") val FarmID: Int,
    @SerializedName("UserEmail") val UserEmail: String,
    @SerializedName("OwnerEmail") val OwnerEmail: String,
    @SerializedName("Term") val Term: String,
    @SerializedName("createAt") val createAt: String,
    @SerializedName("updateAt") val updateAt: String
)
