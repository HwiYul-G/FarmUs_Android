package com.example.farmus_application.model.farm.list

import com.google.gson.annotations.SerializedName

data class ListResult(
    @SerializedName("Name") val Name: String,
    @SerializedName("Price") val Price: Int,
    @SerializedName("SquaredMeters") val SquaredMeters: Int,
    @SerializedName("Picture_url") val Picture_url: String?,
    @SerializedName("Picture_key") val Picture_key: String?,
    @SerializedName("Views") val Views: Int,
    @SerializedName("Star") val Star: Int,
    @SerializedName("Likes") val Likes: Int,
    @SerializedName("createAt") val createAt: String,
    @SerializedName("updateAt") val updateAt: String,
    @SerializedName("Owner") val Owner: String,
    @SerializedName("LocationBig") val LocationBig: String,
    @SerializedName("LocationMid") val LocationMid: String,
    @SerializedName("LocationSmall") val LocationSmall: String,
    @SerializedName("FarmID") val FarmID: Int,
    @SerializedName("Description") val Description: String,
    @SerializedName("Status") val Status: String,
    @SerializedName("startAt") val startAt: String,
    @SerializedName("endAt") val endAt: String,
    @SerializedName("Category") val Category: String,
    @SerializedName("Tag") val Tag: String
)
