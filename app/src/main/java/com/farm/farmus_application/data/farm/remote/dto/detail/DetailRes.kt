package com.farm.farmus_application.data.farm.remote.dto.detail

data class DetailRes(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: com.farm.farmus_application.data.farm.remote.dto.detail.DetailResult
)