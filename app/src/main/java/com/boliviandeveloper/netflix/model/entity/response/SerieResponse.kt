package com.boliviandeveloper.netflix.model.entity.response

import com.boliviandeveloper.netflix.model.entity.Serie
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SerieResponse(
    @SerializedName("serie")
    val serie: Serie
): Serializable