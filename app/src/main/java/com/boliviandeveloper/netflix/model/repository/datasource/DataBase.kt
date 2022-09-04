package com.boliviandeveloper.netflix.model.repository.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.boliviandeveloper.netflix.model.entity.Session
import com.boliviandeveloper.netflix.model.entity.User
import com.boliviandeveloper.netflix.model.repository.datasource.local.SessionDao
import com.boliviandeveloper.netflix.model.repository.datasource.local.UserDao

@Database(entities = [User::class, Session::class], version = 1)
abstract class DataBase: RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun sessionDao() : SessionDao

}