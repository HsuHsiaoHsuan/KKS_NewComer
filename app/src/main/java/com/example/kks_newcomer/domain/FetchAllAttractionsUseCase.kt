package com.example.kks_newcomer.domain

import androidx.paging.PagingData
import com.example.kks_newcomer.data.Attraction
import com.example.kks_newcomer.data.TourRepository
import com.example.kks_newcomer.di.IoDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class FetchAllAttractionsUseCase @Inject constructor(
    private val tourRepository: TourRepository,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    operator fun invoke(lang: String = "zh-tw", page: Int = 1): Flow<PagingData<Attraction>> =
        tourRepository.fetchAllAttractionsPaged().flowOn(defaultDispatcher)
}