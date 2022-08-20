package com.boliviandeveloper.netflix.model.repository.datasource.remote

import com.boliviandeveloper.netflix.model.entity.Response
import com.boliviandeveloper.netflix.model.entity.request.AuthRequest
import com.boliviandeveloper.netflix.model.entity.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {

    @POST("v1/user/auth")
    suspend fun auth(@Header("Authorization") authorization: String, @Body authRequest: AuthRequest): Response<AuthResponse>

}