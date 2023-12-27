package com.farm.farmus_application.data.reserve.remote.dto.reserve_list

data class ReserveListRes(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<ReserveListResult>
)