package com.farm.farmus_application.data.farm.remote.dto.list

import com.google.gson.annotations.SerializedName

data class ListRes(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<ListResult>,
)
