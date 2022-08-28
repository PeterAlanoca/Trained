package com.boliviandeveloper.netflix.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Serie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("trailer_url")
    val trailerUrl: String,
    @SerializedName("trailer_thumbnail_url")
    val trailerThumbnailUrl: String,
    @SerializedName("logo_url")
    val logoUrl: String,
    @SerializedName("cover_url")
    val coverUrl: String,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("cast")
    val cast: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("coincidence")
    val coincidence: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("resolution")
    val resolution: String,
    @SerializedName("seasons_count")
    val seasonsCount: String
) : Serializable