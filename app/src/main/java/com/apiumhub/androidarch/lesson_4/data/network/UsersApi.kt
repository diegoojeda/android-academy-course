package com.apiumhub.androidarch.lesson_4.data.network

import com.apiumhub.androidarch.lesson_4.data.network.dto.UserNetworkDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UsersApi {
    @GET("/users")
    suspend fun getUsersList(): List<UserNetworkDto>

    companion object {
        fun create(): UsersApi = Retrofit
            .Builder()
            .baseUrl("https://apiumacademy.serveo.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersApi::class.java)
    }
}
