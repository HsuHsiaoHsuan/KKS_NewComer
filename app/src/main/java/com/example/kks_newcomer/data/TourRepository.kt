package com.example.kks_newcomer.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kks_newcomer.data.network.AttractionPagingSource
import com.example.kks_newcomer.data.network.TourApi
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class TourRepository @Inject constructor(
    private val tourApi: TourApi
) {

    fun fetchAllAttractionsPaged(): Flow<PagingData<Attraction>> {
        return Pager(
            config = PagingConfig(pageSize = 30, enablePlaceholders = false),
            pagingSourceFactory = { AttractionPagingSource(tourApi) }
        ).flow
    }
}