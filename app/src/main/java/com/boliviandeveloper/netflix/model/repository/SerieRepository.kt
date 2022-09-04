package com.boliviandeveloper.netflix.model.repository

import android.content.Context
import com.boliviandeveloper.netflix.helper.http.Authorization
import com.boliviandeveloper.netflix.model.entity.Response
import com.boliviandeveloper.netflix.model.entity.response.SerieResponse
import com.boliviandeveloper.netflix.model.repository.datasource.Resource
import com.boliviandeveloper.netflix.model.repository.datasource.remote.SerieService
import javax.inject.Inject

class SerieRepository @Inject constructor(private val context: Context, private val authorization: Authorization, private val serieService: SerieService) {

    suspend fun get(id: Int): Resource<SerieResponse?> {
        return try {
            val response = serieService.get(authorization.token, id)
            if (response.state == Response.SUCCESS) {
                Resource.success(response.data, response.message)
            } else {
                Resource.error(response.message)
            }
        } catch (e: Exception) {
            Resource.error(e, context)
        }
    }
}