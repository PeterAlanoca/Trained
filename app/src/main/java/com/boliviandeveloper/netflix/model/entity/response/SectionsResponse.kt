package com.boliviandeveloper.netflix.model.entity.response

import com.boliviandeveloper.netflix.model.entity.Section
import com.boliviandeveloper.netflix.model.entity.Serie
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SectionsResponse(
    @SerializedName("cover")
    val cover: Serie,
    @SerializedName("sections")
    val sections: List<Section>,
) : Serializable
