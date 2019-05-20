package com.apiumhub.androidarch.lesson_4.domain

import arrow.core.Try
import com.apiumhub.androidarch.lesson_4.data.db.UsersRoomRepository
import com.apiumhub.androidarch.lesson_4.data.network.UsersRetrofitRepository
import com.apiumhub.androidarch.lesson_4.domain.entity.User

class GetUsers(
    private val networkRepository: UsersRetrofitRepository,
    private val databaseRepository: UsersRoomRepository
) {
    suspend fun execute(): Try<List<User>> {
        return networkRepository.getAll()
    }
}