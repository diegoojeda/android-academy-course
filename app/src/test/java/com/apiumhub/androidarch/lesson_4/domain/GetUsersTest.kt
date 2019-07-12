package com.apiumhub.androidarch.lesson_4.domain

import arrow.core.Try
import com.apiumhub.androidarch.lesson_4.data.db.UsersRoomRepository
import com.apiumhub.androidarch.lesson_4.data.network.UsersRetrofitRepository
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import com.apiumhub.androidarch.lesson_4.domain.exception.NoInternetConnectionException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class GetUsersTest {

    private lateinit var getUsers: GetUsers
    private val networkRepository = mockk<UsersRetrofitRepository>()
    private val dbRepository = mockk<UsersRoomRepository>(relaxed = true)

    private val usersList = listOf(User(UUID.randomUUID().toString(), "", ""))

    @Before
    fun setup() {
        getUsers = GetUsers(networkRepository, dbRepository)
    }

    @Test
    fun `should load users from network`() = runBlocking {
        mockNetworkSuccess()

        val actual = getUsers.execute()

        assertEquals(Try.just(usersList), actual)

        Unit
    }

    @Test
    fun `should load users from database when users were already loaded before`() = runBlocking {
        coEvery {
            networkRepository.getAll()
        } returns Try.raiseError(NoInternetConnectionException())
        coEvery {
            dbRepository.getAll()
        } returns Try.just(usersList)

        val actual = getUsers.execute()

        assertEquals(Try.just(usersList), actual)

        Unit
    }

    @Test
    fun `should save in database when read from network`() = runBlocking {
        mockNetworkSuccess()

        getUsers.execute()

        coVerify {
            dbRepository.saveAll(usersList)
        }

        Unit
    }

    private fun mockNetworkSuccess() {
        coEvery {
            networkRepository.getAll()
        } returns Try.just(usersList)
    }
}