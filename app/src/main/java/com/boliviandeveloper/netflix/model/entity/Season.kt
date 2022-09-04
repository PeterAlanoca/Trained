package com.boliviandeveloper.netflix.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Season(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("episodes")
    val episodes: List<Episode>
) : Serializable