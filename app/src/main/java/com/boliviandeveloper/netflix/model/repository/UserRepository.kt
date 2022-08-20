package com.boliviandeveloper.netflix.model.repository

import android.content.Context
import com.boliviandeveloper.netflix.helper.http.Authorization
import com.boliviandeveloper.netflix.model.entity.Response
import com.boliviandeveloper.netflix.model.entity.request.AuthRequest
import com.boliviandeveloper.netflix.model.entity.response.AuthResponse
import com.boliviandeveloper.netflix.model.repository.datasource.Resource
import com.boliviandeveloper.netflix.model.repository.datasource.remote.UserService
import java.lang.Exception
import javax.inject.Inject

class UserRepository @Inject constructor(private val context: Context, private val authorization: Authorization, val userService: UserService) {

    suspend fun auth(authRequest: AuthRequest): Resource<AuthResponse> {
        return try {
            val response = userService.auth(authorization.basic, authRequest)
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