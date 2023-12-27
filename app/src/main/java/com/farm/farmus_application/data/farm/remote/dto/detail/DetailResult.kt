package com.farm.farmus_application.data.farm.remote.dto.detail

import com.farm.farmus_application.model.farm.list.Pictures

data class DetailResult(
    val Description: String,
    val FarmID: Int,
    val Likes: Int,
    val LocationBig: String,
    val LocationMid: String,
    val LocationSmall: String,
    val Name: String,
    val PictureObject: List<Pictures>?,
    val Price: Int,
    val SquaredMeters: Int,
    val Star: Int,
    val Status: String,
    val Views: Int,
    val farmer: Farmer
)