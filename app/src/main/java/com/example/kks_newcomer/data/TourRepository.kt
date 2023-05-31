package com.example.kks_newcomer.data

import com.example.kks_newcomer.data.network.TourDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TourRepository @Inject constructor(
    private val tourDataSource: TourDataSource
) {

    fun fetchAllAttractions(lang: String, page: Int): Flow<List<Attraction>> =
        tourDataSource.fetchAllAttractions(lang = lang, page = page).mapLatest { response ->
            return@mapLatest response.allAttractionsData.map { dto ->
                dto.toAttraction()
            }
        }
}