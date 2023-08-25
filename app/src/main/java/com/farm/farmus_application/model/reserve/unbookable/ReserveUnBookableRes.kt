package com.farm.farmus_application.model.reserve.unbookable

data class ReserveUnBookableRes(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<UnBookableResult>?
)