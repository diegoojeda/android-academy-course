package com.apiumhub.androidarch.lesson_4.data.db

import arrow.core.Try
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import com.apiumhub.androidarch.lesson_4.domain.repositories.ReadRepository
import com.apiumhub.androidarch.lesson_4.domain.repositories.WriteRepository

class UsersRoomRepository(
    private val userDao: UserDao,
    private val toDomain: (UserDbEntity) -> User
) : ReadRepository<User>, WriteRepository<User> {

    override suspend fun getAll(): Try<List<User>> =
        Try { userDao.getAll().map(toDomain) }

    override suspend fun saveAll(items: List<User>) {
        userDao.insertAll(items.map { UserDbEntity(it.id, it.alias, it.avatar) })
    }
}