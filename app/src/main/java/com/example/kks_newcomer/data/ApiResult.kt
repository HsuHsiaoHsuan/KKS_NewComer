package com.example.kks_newcomer.data

sealed class ApiResult<out T> { // TODO (FREEMAN) why `out`
    object Loading : ApiResult<Nothing>()
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Failure(val message: String? = null) : ApiResult<Nothing>()
    object NetworkError : ApiResult<Nothing>()
}