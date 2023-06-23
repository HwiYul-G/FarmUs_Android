package com.example.farmus_application.model.user

import kotlinx.serialization.Serializable

@Serializable
data class FindAccountReq(
    val name: String,
    val phoneNumber : String
)