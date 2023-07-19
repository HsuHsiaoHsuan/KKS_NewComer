package com.example.kks_newcomer.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
data class AllAttractionsResponse(
    val total: Int,

    @Json(name = "data")
    val allAttractionsData: List<AttractionDto>
)

@JsonClass(generateAdapter = true)
data class AttractionDto(
    val id: Long,
    val name: String?,

    @Json(name = "name_zh")
    val nameZh: String?,

    @Json(name = "open_status")
    val openStatus: Int?,

    val introduction: String?,

    @Json(name = "open_time")
    val openTime: String?,

    val zipcode: String?,
    val distric: String?,
    val address: String?,
    val tel: String?,
    val fax: String?,
    val email: String?,
    val months: String?,
    @Json(name = "nlat")
    val latitude: Double?,
    @Json(name = "elong")
    val longitude: Double?,

    @Json(name = "official_site")
    val officialSite: String?,

    val facebook: String?,
    val ticket: String?,
    val remind: String?,
    val staytime: String?,
    val modified: String?,
    val url: String?,
    val category: List<Category>?,
    val target: List<Category>?,
    val service: List<Category>?,
    val friendly: List<Category>?,
    val images: List<Image>?,
    val files: List<Any?>?,
    val links: List<Link>?
)

@Parcelize
@JsonClass(generateAdapter = true)
data class Image(
    val src: String,
    val subject: String,
    val ext: String
): Parcelable

@JsonClass(generateAdapter = true)
data class Link(
    val src: String,
    val subject: String
)

fun AttractionDto.toAttraction(): Attraction = Attraction(
    id = this.id,
    name = this.name,
    introduction = this.introduction,
    address = this.address,
    tel = this.tel,
    latitude = this.latitude,
    longitude = this.longitude,
    officialSite = this.officialSite,
    facebook = this.facebook,
    remind = this.remind,
    url = this.url,
    images = this.images

)