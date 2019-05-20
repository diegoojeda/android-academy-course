package com.apiumhub.androidarch

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.apiumhub.androidarch.lesson_4.data.db.UserDao
import com.apiumhub.androidarch.lesson_4.data.db.UserDbEntity

const val DATA_BASE_NAME = "academy-db"

@Database(
    entities = [UserDbEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

fun appDb(context: Context): AppDatabase = Room.databaseBuilder(
    context,
    AppDatabase::class.java,
    DATA_BASE_NAME
).build()