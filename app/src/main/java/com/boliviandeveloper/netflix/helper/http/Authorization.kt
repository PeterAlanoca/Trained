package com.boliviandeveloper.netflix.helper.http

import com.boliviandeveloper.netflix.model.repository.datasource.local.SessionDao
import okhttp3.Credentials

data class Authorization(private val user: String, private val password: String, private val sessionDao: SessionDao) {

    val basic = Credentials.basic(user, password)

    val token: String
    get() {
        val session = sessionDao.get()
        return if (session != null) {
            "Bearer " + session.token
        } else {
            ""
        }
    }
}