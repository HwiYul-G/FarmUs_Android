package com.example.farmus_application.model.unbookable

data class ReserveUnBookableRes(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<UnBookableResult>
)