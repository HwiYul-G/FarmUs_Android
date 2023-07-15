package com.example.farmus_application.model.farm.detail

data class DetailRes(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: DetailResult
)