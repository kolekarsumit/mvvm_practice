package com.example.mvvm.utils

sealed class FlowResult<out T> {
    data class Success<out T>(val data: T): FlowResult<T>()

    data class Failure(
        val msg: String?,
        val code: Int?
    ): FlowResult<Nothing>()

    object Loading: FlowResult<Nothing>()

}