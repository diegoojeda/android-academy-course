package com.apiumhub.androidarch.lesson_4.domain

import arrow.core.Either
import com.apiumhub.androidarch.lesson_4.domain.exception.DomainError
import com.apiumhub.androidarch.lesson_6.common.User
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class GetUsersTest {

    private lateinit var getUsers: GetUsers
    private val getFromNetwork = mockk<suspend () -> Either<DomainError, List<User>>>(relaxed = true)
    private val getFromDb = mockk<suspend () -> Either<DomainError, List<User>>>(relaxed = true)
    private val storeOnDb = mockk<(List<User>) -> Unit>(relaxed = true)

    private val dummyUser = User(UUID.randomUUID().toString(), "alias1", "https://api.adorable.io/avatars/123")

    private val usersList = listOf(dummyUser)

    @Before
    fun setup() {
        getUsers = GetUsers(getFromNetwork, getFromDb, storeOnDb)
    }

    @Test
    fun shouldLoadUsersFromNetwork() = runBlocking {
        TODO()
        Unit
    }

    @Test
    fun shouldLoadUsersFromDb() = runBlocking {
        TODO()
        Unit
    }
}