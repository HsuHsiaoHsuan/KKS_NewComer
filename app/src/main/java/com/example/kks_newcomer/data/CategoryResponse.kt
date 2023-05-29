package com.example.kks_newcomer.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    val total: Int,

    @Json(name = "data")
    val categoryData: CategoryData
)

@JsonClass(generateAdapter = true)
data class CategoryData(
    @Json(name = "Accessibility")
    val accessibility: List<Category>?,

    @Json(name = "Accommodation")
    val accommodation: List<Category>?,

    @Json(name = "AccommodationServices")
    val accommodationServices: List<Category>?,

    @Json(name = "Activity")
    val activity: List<Category>?,

    @Json(name = "Category")
    val category: List<Category>?,

    @Json(name = "Consume")
    val consume: List<Category>?,

    @Json(name = "Event")
    val event: List<Category>?,

    @Json(name = "FeaturePosts")
    val featurePosts: List<Category>?,

    @Json(name = "Friendly")
    val friendly: List<Category>?,

    @Json(name = "Gourmet")
    val gourmet: List<Category>?,

    @Json(name = "Services")
    val services: List<Category>?,

    @Json(name = "Target")
    val target: List<Category>?
)

@JsonClass(generateAdapter = true)
data class Category (
    val id: Long,

    val name: String
?)
