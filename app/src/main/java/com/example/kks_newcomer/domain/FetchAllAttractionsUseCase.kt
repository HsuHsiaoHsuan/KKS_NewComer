package com.example.kks_newcomer.domain

import com.example.kks_newcomer.data.TourRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchAllAttractionsUseCase @Inject constructor(
    private val tourRepository: TourRepository
) {
    // TODO FREEMAN why use `operator`, `invoke`
    suspend operator fun invoke(lang: String = "zh-tw", page: Int = 1) =
        tourRepository.fetchAllAttractions(lang = lang, page = page)
}