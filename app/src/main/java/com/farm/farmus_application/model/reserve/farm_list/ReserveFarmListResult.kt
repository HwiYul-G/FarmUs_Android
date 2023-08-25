package com.farm.farmus_application.model.reserve.farm_list

data class ReserveFarmListResult(
    val FarmID: Int,
    val UserEmail: String,
    val OwnerEmail: String,
    val Term: String,
    val createAt: String,
    val updateAt: String
)
