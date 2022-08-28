package com.boliviandeveloper.netflix.helper.http

import okhttp3.Credentials

data class Authorization(private val user: String, private val password: String) {

    val basic = Credentials.basic(user, password)

    val token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3d3dy5hcGkubmV0ZmxpeC5ib2xpdmlhbmRldmVsb3Blci5jb20vdjEvdXNlci9hdXRoIiwiaWF0IjoxNjYxNjQxODM0LCJleHAiOjE2NjE2NDU0MzQsIm5iZiI6MTY2MTY0MTgzNCwianRpIjoiMlQ1Q1ZYaDQzdHRURVNkMiIsInN1YiI6IjEiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.MCoOuuXsp1RGG_gOnGPC8cHPQwCGiX7pxRd_g20ZCxs"

}