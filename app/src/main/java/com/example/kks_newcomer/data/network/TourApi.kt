package com.example.kks_newcomer.data.network

import com.example.kks_newcomer.data.AllAttractionsResponse
import com.example.kks_newcomer.data.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TourApi {
    // 景點
    @Headers("Accept: application/json")
    @GET("{lang}/Attractions/All")
    suspend fun fetchAllAttractions(
        @Path("lang") language: String,
        @Query("page") page: Int
    ): AllAttractionsResponse

    // 分類查詢
    @Headers("Accept: application/json")
    @GET("{lang}/Miscellaneous/Categories")
    suspend fun fetchCategory(
        @Path("lang") language: String,
        @Query("type") type: String
    ): CategoryResponse
}