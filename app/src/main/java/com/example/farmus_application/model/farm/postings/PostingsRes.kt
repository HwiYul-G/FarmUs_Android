package com.example.farmus_application.model.farm.postings

import com.google.gson.annotations.SerializedName

data class PostingsRes(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: PostingsResult
)
