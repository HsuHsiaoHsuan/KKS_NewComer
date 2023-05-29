package com.example.kks_newcomer

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

sealed class ApiResultWrapper<out T> {
    data class Success<out T>(val value: T) : ApiResultWrapper<T>()
    data class Failure(val code: Int? = null, val error: String? = null) :
        ApiResultWrapper<Nothing>()

    object NetworkError : ApiResultWrapper<Nothing>()
}

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ApiResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ApiResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ApiResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse =
                        throwable.response()?.errorBody()?.string() ?: "No Error body"
                    ApiResultWrapper.Failure(code, errorResponse)
                }
                else -> {
                    Timber.e("throwable: $throwable")
                    ApiResultWrapper.Failure(null, null)
                }
            }
        }
    }
}