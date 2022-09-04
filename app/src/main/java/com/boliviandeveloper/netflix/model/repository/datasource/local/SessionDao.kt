package com.boliviandeveloper.netflix.model.repository.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.boliviandeveloper.netflix.model.entity.Session
@Dao
interface SessionDao {

    @Query("SELECT * FROM Session ORDER BY id DESC LIMIT 1")
    fun get(): Session?

    @Insert
    fun insert(session: Session): Long

    @Query("DELETE FROM Session")
    fun deleteAll()
}