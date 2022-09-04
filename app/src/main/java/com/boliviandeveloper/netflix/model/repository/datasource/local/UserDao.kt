package com.boliviandeveloper.netflix.model.repository.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.boliviandeveloper.netflix.model.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User ORDER BY id DESC LIMIT 1")
    suspend fun get(): User?

    @Insert
    suspend fun insert(user: User): Long

    @Query("DELETE FROM User")
    suspend fun deleteAll()
}