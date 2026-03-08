package com.example.mvvm.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform


fun <T> Flow<FlowResult<T>>.doOnSuccess(
    action: suspend (T) -> Unit
): Flow<FlowResult<T>> =
    transform { result ->
        if(result is FlowResult.Success){
            action(result.data)
        }
        emit(result)
    }

fun <T> Flow<FlowResult<T>>.doOnFailure(
    action: suspend (String?) -> Unit
): Flow<FlowResult<T>> =
    transform { result ->
        if(result is FlowResult.Failure){
            action(result.msg)
        }
        emit(result)
    }

fun <T> Flow<FlowResult<T>>.doOnLoading(
    action: suspend () -> Unit
): Flow<FlowResult<T>> =
    transform { result ->
        if(result is FlowResult.Loading){
            action()
        }
        emit(result)
    }