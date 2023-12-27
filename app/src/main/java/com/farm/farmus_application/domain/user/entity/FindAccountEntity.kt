package com.farm.farmus_application.domain.user.entity

data class FindAccountEntity(
    val result : Boolean,
    val name : String,
    val email : String,
    val status : String,
)
