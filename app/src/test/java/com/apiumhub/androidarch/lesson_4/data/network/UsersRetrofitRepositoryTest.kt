package com.apiumhub.androidarch.lesson_4.data.network

import arrow.core.getOrElse
import com.apiumhub.androidarch.lesson_4.data.network.dto.UserNetworkDto
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersRetrofitRepositoryTest {

    private val api: UsersApi = Retrofit
        .Builder()
        .baseUrl("https://apiumacademy.serveo.net")
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UsersApi::class.java)

    private lateinit var sut: UsersRetrofitRepository

    @Before
    fun setup() {
        sut = UsersRetrofitRepository(api, UserNetworkDto::toDomain)
    }

    @Test
    fun shouldReadUsersFromNetwork() = runBlocking {
        val users = sut.getAll().getOrElse { emptyList() }
        assertFalse(users.isEmpty())
        Unit
    }
}