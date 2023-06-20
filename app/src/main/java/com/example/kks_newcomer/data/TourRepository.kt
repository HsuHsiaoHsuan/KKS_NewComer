package com.example.kks_newcomer.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kks_newcomer.data.network.AttractionPagingSource
import com.example.kks_newcomer.data.network.TourApi
import com.example.kks_newcomer.data.network.TourDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TourRepository @Inject constructor(
    private val tourApi: TourApi
) {

    fun fetchAllAttractionsPaged(pagingConfig: PagingConfig = PagingConfig(pageSize = 30)) : Flow<PagingData<Attraction>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { AttractionPagingSource(tourApi) }
        ).flow
    }
}