package com.boliviandeveloper.netflix.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Episode(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("video_url")
    val videoUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("duration")
    val duration: String
) : Serializable