package com.farm.farmus_application.model.favorite

import com.google.gson.annotations.SerializedName

data class FavoriteFarmRes(
    @SerializedName("result") val result: Boolean,
    @SerializedName("farmSize") val farmSize: Int,
    @SerializedName("farmList") val farmList : List<FavoriteFarm>
)