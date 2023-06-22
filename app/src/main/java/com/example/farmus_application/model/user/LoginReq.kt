package com.example.farmus_application.model.user

import kotlinx.serialization.Serializable

@Serializable
data class LoginReq(
    val email: String,
    val password: String
)