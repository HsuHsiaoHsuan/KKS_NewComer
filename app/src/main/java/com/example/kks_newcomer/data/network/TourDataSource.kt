package com.example.kks_newcomer.data.network

import androidx.paging.Pager
import com.example.kks_newcomer.data.AllAttractionsResponse
import com.example.kks_newcomer.data.Attraction
import com.example.kks_newcomer.data.CategoryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TourDataSource @Inject constructor(
    private val tourApi: TourApi
) {

    fun fetchAllAttractions(lang: String, page: Int): Flow<AllAttractionsResponse> = flow {
        emit(tourApi.fetchAllAttractions(language = lang, page = page))
    }

    fun fetchCategory(lang: String, type: String): Flow<CategoryResponse> = flow {
        emit(tourApi.fetchCategory(language = lang, type = type))
    }
}