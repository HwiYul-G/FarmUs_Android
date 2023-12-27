package com.farm.farmus_application.data.reserve.remote.dto.unbookable

data class ReserveUnBookableRes(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<UnBookableResult>?
)