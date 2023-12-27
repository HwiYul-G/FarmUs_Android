package com.farm.farmus_application.data.farm.remote.dto.postings

import com.google.gson.annotations.SerializedName

data class PostingsResult(
    @SerializedName("newFarmID") val newFarmID: Int,
    @SerializedName("districtCode") val distrit: String
)
