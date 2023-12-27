package com.farm.farmus_application.data.farm.remote.dto.list

import com.google.gson.annotations.SerializedName

data class ListResult(
    @SerializedName("FarmID") val FarmID: Int,
    @SerializedName("Name") val Name: String,
    @SerializedName("Price") val Price: Int,
    @SerializedName("SquaredMeters") val SquaredMeters: Int,
    @SerializedName("Views") val Views: Int,
    @SerializedName("Star") val Star: Int,
    @SerializedName("Likes") val Likes: Int,
    @SerializedName("Status") val Status: String,
    @SerializedName("Pictures") val Pictures: List<Pictures>,
    @SerializedName("Liked") val Liked: Boolean
)
