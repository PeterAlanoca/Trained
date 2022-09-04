package com.boliviandeveloper.netflix.model.repository

import android.content.Context
import com.boliviandeveloper.netflix.helper.http.Authorization
import com.boliviandeveloper.netflix.model.entity.Response
import com.boliviandeveloper.netflix.model.entity.Session
import com.boliviandeveloper.netflix.model.entity.User
import com.boliviandeveloper.netflix.model.entity.request.AuthRequest
import com.boliviandeveloper.netflix.model.entity.response.AuthResponse
import com.boliviandeveloper.netflix.model.repository.datasource.Resource
import com.boliviandeveloper.netflix.model.repository.datasource.local.SessionDao
import com.boliviandeveloper.netflix.model.repository.datasource.local.UserDao
import com.boliviandeveloper.netflix.model.repository.datasource.remote.UserService
import java.lang.Exception
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val context: Context,
    private val authorization: Authorization,
    private val userDao: UserDao,
    private val sessionDao: SessionDao,
    private val userService: UserService) {

    suspend fun auth(authRequest: AuthRequest): Resource<AuthResponse?> {
        return try {
            val response = userService.auth(authorization.basic, authRequest)
            if (response.state == Response.SUCCESS) {
                response.data?.let { data ->
                    sessionDao.deleteAll()
                    sessionDao.insert(Session(data.token))
                    deleteAll()
                    insert(data.user)
                }
                Resource.success(response.data, response.message)
            } else {
                Resource.error(response.message)
            }
        } catch (e: Exception) {
            Resource.error(e, context)
        }
    }

    suspend fun get(): Resource<User?> {
        return try {
            val user = userDao.get()
            if (user != null) {
                Resource.success(user, "success")
            } else {
                Resource.error("error")
            }
        } catch (e: Exception) {
            Resource.error(e, context)
        }
    }

    private suspend fun insert(user: User): Resource<Long?> {
        return try {
            val id = userDao.insert(user)
            if (id > 0) {
                Resource.success(id, "success")
            } else {
                Resource.error("error")
            }
        } catch (e: Exception) {
            Resource.error(e, context)
        }
    }

    private suspend fun deleteAll() {
        userDao.deleteAll()
    }
}