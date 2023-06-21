package com.example.farmus_application.model.user

data class LoginResult(
    val accesstoken: String,
    val email: String,
    val name: String,
    val nickName: String,
    val role: String,
    val status: Boolean
)