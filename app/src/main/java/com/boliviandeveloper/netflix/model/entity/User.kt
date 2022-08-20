package com.boliviandeveloper.netflix.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String
) : Serializable
