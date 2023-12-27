package com.farm.farmus_application.domain

sealed class BaseResult<out T : Any, out U : Any> {
    data class Success<out T : Any>(val data: T) : BaseResult<T, Nothing>()
    data class Error<out U : Any>(val error: U) : BaseResult<Nothing, U>()
}
