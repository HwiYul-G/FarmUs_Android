package com.example.farmus_application.model.user

data class LoginRes(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: LoginResult
)