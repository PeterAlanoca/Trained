package com.boliviandeveloper.netflix.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Session(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var token: String
) {
    constructor(token: String) : this(0, token)
}