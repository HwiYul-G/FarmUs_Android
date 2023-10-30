package com.farm.farmus_application.model.farm.editinfo

import com.farm.farmus_application.model.farm.postings.PostingsResult
import com.google.gson.annotations.SerializedName

data class EditinfoRes(
    @SerializedName("result") val result: Boolean,
    @SerializedName("code") val code: Int?,
    @SerializedName("message") val message: String?
)
