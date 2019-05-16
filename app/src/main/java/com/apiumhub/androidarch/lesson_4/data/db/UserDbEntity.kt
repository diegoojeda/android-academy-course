package com.apiumhub.androidarch.lesson_4.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserDbEntity(
    @PrimaryKey var id: String,
    var alias: String,
    var avatar: String
)