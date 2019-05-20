package com.apiumhub.androidarch.lesson_4.domain

import com.apiumhub.androidarch.lesson_4.data.db.UsersRoomRepository
import com.apiumhub.androidarch.lesson_4.data.network.UsersRetrofitRepository
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class GetUsersTest {

    private lateinit var getUsers: GetUsers
    private val networkRepository = mockk<UsersRetrofitRepository>()
    private val dbRepository = mockk<UsersRoomRepository>()

    @Before
    fun setup() {
        getUsers = GetUsers(networkRepository, dbRepository)
    }

    @Test
    fun shouldLoadUsersFromNetwork() {
        TODO()
    }

    @Test
    fun shouldLoadUsersFromDb() {
        TODO()
    }

    @Test
    fun shouldStoreUsersOnDb() {
        TODO()
    }
}