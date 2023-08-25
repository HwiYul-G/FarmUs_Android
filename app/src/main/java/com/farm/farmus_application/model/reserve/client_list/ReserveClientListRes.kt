package com.farm.farmus_application.model.reserve.client_list

import com.google.gson.annotations.SerializedName

data class ReserveClientListRes(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<ReserveClientListResult>?
)
