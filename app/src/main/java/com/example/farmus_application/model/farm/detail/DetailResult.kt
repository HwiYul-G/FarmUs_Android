package com.example.farmus_application.model.farm.detail

data class DetailResult(
    val Description: String,
    val FarmID: Int,
    val Likes: Int,
    val LocationBig: String,
    val LocationMid: String,
    val LocationSmall: String,
    val Name: String,
    val PictureObject: Any,
    val Price: Int,
    val SquaredMeters: Int,
    val Star: Int,
    val Status: String,
    val Views: Int,
    val farmer: Farmer
)