package com.farmus.farmus_application.model.farm.myfarm

data class MyFarmItem(
    val FarmID: Int,
    val Likes: Int,
    val LocationBig: String,
    val LocationMid: String,
    val LocationSmall: String?,
    val Name: String,
    val Picture_url: String?,
    val Price: Int,
    val SquaredMeters: Int
)