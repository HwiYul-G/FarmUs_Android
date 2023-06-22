package com.example.farmus_application.model.user

data class SignUpVerificationRes(
    val code: Int,
    val message: String,
    val result: Boolean
)