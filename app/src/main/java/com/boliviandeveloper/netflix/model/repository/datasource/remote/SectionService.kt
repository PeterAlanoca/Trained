package com.boliviandeveloper.netflix.model.repository.datasource.remote

import com.boliviandeveloper.netflix.model.entity.Response
import com.boliviandeveloper.netflix.model.entity.response.SectionsResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface SectionService {

    @GET("v1/section/get-all")
    suspend fun getAll(@Header("Authorization") authorization: String): Response<SectionsResponse>
}