package com.farmus.farmus_application.model.farm.list

import com.google.gson.annotations.SerializedName

data class ListRes(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<ListResult>,
)
