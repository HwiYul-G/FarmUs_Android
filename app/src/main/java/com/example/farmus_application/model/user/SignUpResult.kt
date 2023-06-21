package com.example.farmus_application.model.user

import kotlinx.serialization.Serializable

@Serializable
data class SignUpResult(
    val email: String,
    val role: String
)