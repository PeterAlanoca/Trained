package com.boliviandeveloper.netflix.model.repository

import android.content.Context
import com.boliviandeveloper.netflix.helper.http.Authorization
import com.boliviandeveloper.netflix.model.entity.Response
import com.boliviandeveloper.netflix.model.entity.response.SectionsResponse
import com.boliviandeveloper.netflix.model.repository.datasource.Resource
import com.boliviandeveloper.netflix.model.repository.datasource.remote.SectionService
import java.lang.Exception
import javax.inject.Inject

class SectionRepository @Inject constructor(private val context: Context, private val authorization: Authorization, private val sectionService: SectionService) {

    suspend fun getAll(): Resource<SectionsResponse?> {
        return try {
            val response = sectionService.getAll(authorization.token)
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