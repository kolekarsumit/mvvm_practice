package com.example.mvvm.utils

import android.net.http.UrlRequest.Status

data class FlowResponse <out T>(
    val status: Status,
    val data: T? = null,
    val error : String? = null
){
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object{
        fun <T> loading(): FlowResponse<T> =
            FlowResponse(Status.LOADING)

        fun <T> success(data: T): FlowResponse<T> =
            FlowResponse(Status.SUCCESS, data)

        fun <T> error(message: String?): FlowResponse<T> =
            FlowResponse(Status.ERROR, null, message)
    }
}