package com.farmus.farmus_application.model.farm.detail

data class Farmer(
    val Email: String,
    val Name: String,
    val NickName: String?,
    val PhoneNumber: String,
    val Picture_key: String?,
    val Picture_url: String?
)