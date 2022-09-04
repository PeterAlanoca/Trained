package com.boliviandeveloper.netflix.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Response<T>(
    @SerializedName("message")
    val message: String,
    @SerializedName("state")
    val state: Int,
    @SerializedName("data")
    val data: T? = null
) : Serializable {

    companion object {
        const val SUCCESS = 0
    }
}
