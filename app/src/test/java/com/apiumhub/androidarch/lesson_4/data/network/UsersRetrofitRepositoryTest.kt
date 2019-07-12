package com.apiumhub.androidarch.lesson_4.data.network

import junit.framework.TestCase.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersRetrofitRepositoryTest {

    private val api: UsersApi = Retrofit
        .Builder()
        .baseUrl("https://apiumacademy.serveo.net")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UsersApi::class.java)

    private lateinit var sut: UsersRetrofitRepository

    @Before
    fun setup() {
        sut = UsersRetrofitRepository(api)
    }

    @Test
    fun shouldReadUsersFromNetwork() = runBlocking {
        val actual = sut.getAll()

        assertTrue(actual.isSuccess())

        Unit
    }
}