package com.example.farmus_application.model.reserve.reserve_list

data class ReserveListRes(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<ReserveListResult>
)