package com.farm.farmus_application.model.farm.search

import com.google.gson.annotations.SerializedName

data class SearchedRes(
    @SerializedName("result") val result : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String,
    @SerializedName("farms") val farms : List<SearchedFarm>
)
