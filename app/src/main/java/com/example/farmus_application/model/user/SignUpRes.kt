package com.example.farmus_application.model.user

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRes(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: SignUpResult
)