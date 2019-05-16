package com.apiumhub.androidarch.lesson_4.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserDbEntity>): List<Long>

    @Query(value = "SELECT * FROM User")
    suspend fun getAll(): List<UserDbEntity>

    @Query(value = "DELETE FROM User")
    fun deleteAll()

}