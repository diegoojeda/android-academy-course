package com.apiumhub.androidarch.lesson_4.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.apiumhub.androidarch.AppDatabase
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDao = db.userDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun shouldInsertUsersAndThenReadThemSuccessfully() = runBlocking {
        val expected = listOf(
            UserDbEntity(SOME_ID, SOME_ALIAS, SOME_AVATAR),
            UserDbEntity(SOME_OTHER_ID, SOME_ALIAS, SOME_AVATAR)
        )
        userDao.insertAll(expected)
        val actual = userDao.getAll()
        assertThat(actual).isEqualTo(expected)
        Unit
    }

    companion object {
        const val SOME_ID = "someId"
        const val SOME_OTHER_ID = "someOtherId"
        const val SOME_ALIAS = "someAlias"
        const val SOME_AVATAR = "someAvatar"
    }
}