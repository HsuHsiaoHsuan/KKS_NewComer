package com.example.kks_newcomer.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kks_newcomer.data.Attraction
import com.example.kks_newcomer.data.toAttraction
import java.io.IOException
import retrofit2.HttpException

class AttractionPagingSource(private val tourApi: TourApi): PagingSource<Int, Attraction>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Attraction> {
        val page = params.key ?: 1
        return try {
            val data = tourApi.fetchAllAttractions("zh-tw", page)
            LoadResult.Page(
                data = data.allAttractionsData.map { it.toAttraction() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.allAttractionsData.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Attraction>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}