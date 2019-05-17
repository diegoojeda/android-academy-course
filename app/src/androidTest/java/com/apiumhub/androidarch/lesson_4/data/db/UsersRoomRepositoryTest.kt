package com.apiumhub.androidarch.lesson_4.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import arrow.core.getOrElse
import com.apiumhub.androidarch.AppDatabase
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import junit.framework.TestCase.fail
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class UsersRoomRepositoryTest {

    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao
    private lateinit var userRoomRepository: UsersRoomRepository

    private val dummyUser = User(
        SOME_ID,
        SOME_ALIAS,
        SOME_AVATAR
    )

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDao = db.userDao()
        userRoomRepository = UsersRoomRepository(userDao, UserDbEntity::toDomain, User::toDbEntity)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun shouldInsertInDbAndReadAfter() = runBlocking {
        val expected = listOf(dummyUser)
        userRoomRepository.saveAll(expected)
        val actual = userRoomRepository.getAll()
        assertThat(actual.getOrElse { fail() }).isEqualTo(expected)
        Unit
    }

    companion object {
        const val SOME_ID = "3f99e4ec-77d9-11e9-8f9e-2a86e4085a59"
        const val SOME_ALIAS = "someAlias"
        const val SOME_AVATAR = "someAvatar"
    }
}