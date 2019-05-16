package com.apiumhub.androidarch

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apiumhub.androidarch.lesson_4.data.db.UserDao
import com.apiumhub.androidarch.lesson_4.data.db.UserDbEntity

@Database(
    entities = [UserDbEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
