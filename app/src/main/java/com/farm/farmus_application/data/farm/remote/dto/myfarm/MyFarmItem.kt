package com.farm.farmus_application.data.farm.remote.dto.myfarm

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