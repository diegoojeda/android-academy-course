package com.apiumhub.androidarch.lesson_4.data.network

import com.apiumhub.androidarch.lesson_4.data.network.dto.UserNetworkDto
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import com.apiumhub.androidarch.lesson_4.domain.repositories.ReadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRetrofitRepository(
    private val usersApi: UsersApi,
    private val mapper: (UserNetworkDto) -> User
) : ReadRepository<User> {
    override suspend fun getAll(): List<User> =
        withContext(Dispatchers.IO) {
            usersApi.getUsersListAsync().await().map(mapper)
        }
}