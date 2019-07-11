package com.apiumhub.androidarch.lesson_4.data.network

import com.apiumhub.androidarch.lesson_4.data.network.dto.UserNetworkDto
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UsersApi {
    @GET("/users")
    fun getUsersList(): List<UserNetworkDto>

    companion object {
        fun create(): UsersApi = Retrofit
            .Builder()
            .baseUrl("https://apiumacademy.serveo.net")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersApi::class.java)
    }
}
