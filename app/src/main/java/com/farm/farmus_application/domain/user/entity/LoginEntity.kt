package com.farm.farmus_application.domain.user.entity

data class LoginEntity(
    val isSuccess : Boolean,
    val accessToken : String,
    val profile : String,
    val status : Boolean,
)
