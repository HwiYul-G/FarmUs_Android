package com.farm.farmus_application.data.farm.remote.dto.detail

data class Farmer(
    val Email: String,
    val Name: String,
    val NickName: String?,
    val PhoneNumber: String,
    val Picture_key: String?,
    val Picture_url: String?
)