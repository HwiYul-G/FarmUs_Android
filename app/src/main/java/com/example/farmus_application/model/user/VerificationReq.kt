package com.example.farmus_application.model.user

data class VerificationReq(
    val name: String?,
    val phoneNumber: String,
    val usercode: String
)