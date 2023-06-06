package com.example.kks_newcomer.domain

import androidx.paging.PagingData
import com.example.kks_newcomer.data.Attraction
import com.example.kks_newcomer.data.TourRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchAllAttractionsUseCase @Inject constructor(
    private val tourRepository: TourRepository
) {
    operator fun invoke(lang: String = "zh-tw", page: Int = 1): Flow<List<Attraction>> =
        tourRepository.fetchAllAttractions(lang = lang, page = page)
            .flowOn(Dispatchers.IO)

    fun tryPaging(lang: String = "zh-tw", page: Int = 1): Flow<PagingData<Attraction>> =
        tourRepository.fetchAllAttractions2().flowOn(Dispatchers.IO)
}