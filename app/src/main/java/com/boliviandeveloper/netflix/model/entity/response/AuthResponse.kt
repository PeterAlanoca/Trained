package com.boliviandeveloper.netflix.model.entity.response

import com.boliviandeveloper.netflix.model.entity.User
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: User
)
