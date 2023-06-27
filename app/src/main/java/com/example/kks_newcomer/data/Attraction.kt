package com.example.kks_newcomer.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Attraction(
    val id: Long,
    val name: String?,
    val introduction: String?,
    val address: String?,
    val tel: String?,
    val latitude: Double?,
    val longitude: Double?,
    val officialSite: String?,
    val facebook: String?,
    val remind: String?,
    val url: String?,
    val images: List<Image>?
) : Parcelable