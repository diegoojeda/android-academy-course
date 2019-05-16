package com.apiumhub.androidarch.lesson_4.data.network

import com.apiumhub.androidarch.lesson_4.data.network.dto.UserNetworkDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface UsersApi {
    @GET("/users")
    fun getUsersListAsync(): Deferred<List<UserNetworkDto>>
}
