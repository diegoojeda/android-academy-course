package com.apiumhub.androidarch.lesson_4.domain

import arrow.core.Either
import com.apiumhub.androidarch.lesson_4.domain.exception.DomainError
import com.apiumhub.androidarch.lesson_6.common.User
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class GetUsersTest {

    private lateinit var getUsers: GetUsers
    private val getFromNetwork = mockk<suspend () -> Either<DomainError, List<User>>>(relaxed = true)
    private val getFromDb = mockk<suspend () -> Either<DomainError, List<User>>>(relaxed = true)
    private val storeOnDb = mockk<(List<User>) -> Unit>(relaxed = true)

    @Before
    fun setup() {
        getUsers = GetUsers(getFromNetwork, getFromDb, storeOnDb)
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