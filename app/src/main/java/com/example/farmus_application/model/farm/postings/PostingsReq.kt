package com.example.farmus_application.model.farm.postings

import com.google.gson.annotations.SerializedName

data class PostingsReq(
    @SerializedName("name") val name : String,
    @SerializedName("owner") val owner : String,
    @SerializedName("startDate") val startDate : String,
    @SerializedName("endDate") val endDate : String,
    @SerializedName("price") val price : Int,
    @SerializedName("squaredMeters") val squaredMeters : Int,
    @SerializedName("locationBig") val locationBig : String,
    @SerializedName("locationMid") val locationMid : String,
    @SerializedName("locationSmall") val locationSmall : String,
    @SerializedName("description") val description : String,
    @SerializedName("category") val category : String,
    @SerializedName("tag") val tag : String
)
