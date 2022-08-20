package com.boliviandeveloper.netflix.helper.http

import okhttp3.Credentials

data class Authorization(private val user: String, private val password: String) {

    val basic = Credentials.basic(user, password)
}