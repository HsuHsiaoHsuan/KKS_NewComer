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
    private val tourDataSource: TourDataSource,
    private val tourApi: TourApi
) {

    fun fetchAllAttractions(lang: String, page: Int): Flow<List<Attraction>> =
        tourDataSource.fetchAllAttractions(lang = lang, page = page).mapLatest { response ->
            return@mapLatest response.allAttractionsData.map { dto ->
                dto.toAttraction()
            }
        }

    fun fetchAllAttractions2(pagingConfig: PagingConfig = PagingConfig(pageSize = 30)) : Flow<PagingData<Attraction>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { AttractionPagingSource(tourApi) }
        ).flow
    }

    //@OptIn(ExperimentalPagingApi::class)
    //override fun getCoinByPage(pageSize: Int) = Pager(
    //    config = PagingConfig(pageSize),
    //    remoteMediator = coinRemoteMediator
    //) {
    //    val myQuery =
    //        SimpleSQLiteQuery("SELECT * FROM coin_entity")
    //    db.coinDao().getAllCoinsWithPaging(myQuery)
    //}.flow.mapLatest { pagingData ->
    //    pagingData.map { entity ->
    //        entity.toCoinDomainModel()
    //    }
    //}
}