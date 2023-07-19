package com.example.farmus_application.model.reserve.reserve_list

data class ReserveListResult(
    val Farmid: Int,
    val OwnerEmail: String,
    val Reserveid: Int,
    val UserEmail: String,
    val endAt: String,
    val startAt: String
)