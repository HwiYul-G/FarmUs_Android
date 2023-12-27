package com.farm.farmus_application.data.farm.remote.dto.search

import com.google.gson.annotations.SerializedName

data class SearchedFarm(
    @SerializedName("FarmID") val FarmID: Int,
    @SerializedName("Name") val Name: String,
    @SerializedName("Price") val Price: Int,
    @SerializedName("SquaredMeters") val SquaredMeters: Int,
    @SerializedName("LocationBig") val LocationBig: String,
    @SerializedName("LocationMid") val LocationMid: String,
    @SerializedName("LocationSmall") val LocationSmall: String,
    @SerializedName("Likes") val Likes: Int,
    @SerializedName("Picture_url") val Picture_url: String?
)
