package com.example.kks_newcomer.data

data class Attraction(
    val id: Long,
    val name: String,
    val address: String,
    val tel: String,
    val officialSite: String,
    val remind: String,
    val url: String,
    val images: List<Image>
)