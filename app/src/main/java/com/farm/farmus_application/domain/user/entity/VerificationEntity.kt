package com.farm.farmus_application.domain.user.entity

data class VerificationEntity(
    val code : Int,
    val email : String,
    val message : String,
    val name : String,
    val result : Boolean,
)
