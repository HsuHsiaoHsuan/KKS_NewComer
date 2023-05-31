package com.example.kks_newcomer.data

sealed class ApiResult<out T: Any> {
    object Loading : ApiResult<Nothing>()
    data class Success<out T: Any>(val data: T) : ApiResult<T>()
    data class Failure(val message: String? = null) : ApiResult<Nothing>()
    object NetworkError : ApiResult<Nothing>()
}