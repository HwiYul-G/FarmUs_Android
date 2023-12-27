package com.farm.farmus_application.data.farm.remote.dto.favorite

import com.google.gson.annotations.SerializedName

data class FavoriteFarmRes(
    @SerializedName("result") val result: Boolean,
    @SerializedName("farmSize") val farmSize: Int,
    @SerializedName("farmList") val farmList : List<FavoriteFarm>
)