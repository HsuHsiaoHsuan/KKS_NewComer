package com.example.kks_newcomer.data

import com.example.kks_newcomer.data.network.TourDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TourRepository @Inject constructor(
    private val tourDataSource: TourDataSource
) {
    fun fetchAllAttractions(lang: String, page: Int) = flow {
        emit(ApiResult.Loading)
        tourDataSource.fetchAllAttractions(lang = lang, page = page).collectLatest {
            emit(ApiResult.Success(it))
        }
    }.catch {
        emit(ApiResult.Failure(it.message))
    }.flowOn(Dispatchers.IO)

    fun fetchCategory(lang: String, type: String) = flow {
        emit(ApiResult.Loading)
        tourDataSource.fetchCategory(lang = lang, type = type).collectLatest {
            emit(ApiResult.Success(it))
        }
    }.catch {
        emit(ApiResult.Failure(it.message))
    }.flowOn(Dispatchers.IO)
}