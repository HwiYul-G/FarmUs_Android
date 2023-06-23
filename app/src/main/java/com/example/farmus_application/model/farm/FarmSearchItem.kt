package com.example.farmus_application.model.farm


import com.google.gson.annotations.SerializedName

data class FarmSearchItem(
    val createAt: String,
    @SerializedName("FarmName")
    val farmName: String,
    @SerializedName("Likes")
    val likes: Int,
    @SerializedName("Location")
    val location: String,
    @SerializedName("Picture_url")
    val pictureUrl: String,
    @SerializedName("Price")
    val price: Int,
    @SerializedName("SquaredMeters")
    val squaredMeters: Int,
    @SerializedName("Star")
    val star: Int,
    @SerializedName("Views")
    val views: Int
)