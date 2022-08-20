package com.boliviandeveloper.netflix.model.entity.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
) : Serializable