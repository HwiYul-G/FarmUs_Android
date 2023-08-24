package com.example.farmus_application.model.farm.postings

import com.google.gson.annotations.SerializedName

data class PostingsResult(
    @SerializedName("newFarmID") val newFarmID: Int,
    @SerializedName("districtCode") val distrit: String
)
