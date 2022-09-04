package com.boliviandeveloper.netflix.model.repository.datasource.remote

import com.boliviandeveloper.netflix.model.entity.Response
import com.boliviandeveloper.netflix.model.entity.response.SerieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SerieService {

    @GET("v1/serie/get/{id}")
    suspend fun get(@Header("Authorization") authorization: String, @Path("id") id: Int) : Response<SerieResponse>

}