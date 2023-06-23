package com.example.farmus_application.model.user

import java.io.Serializable

data class VerificationRes(
    val code: Int,
    val email: String,
    val message: String,
    val name: String,
    val result: Boolean
):Serializable