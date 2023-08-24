package com.farmus.farmus_application.model.token

data class TokenBody(
    val name : String,
    val nickName : String?,
    val email : String,
    val role : String,
    val phoneNumber : String,
    val profile : String?,
    val iat : Int,
    val exp : Int,
    val iss : String,
)
