package com.apiumhub.androidarch.lesson_4.data.db

import arrow.core.Either
import arrow.core.right
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import com.apiumhub.androidarch.lesson_4.domain.exception.DomainError
import com.apiumhub.androidarch.lesson_4.domain.repositories.ReadRepository
import com.apiumhub.androidarch.lesson_4.domain.repositories.WriteRepository

class UsersRoomRepository(
    private val userDao: UserDao,
    private val toDomain: (UserDbEntity) -> User,
    private val toDb: (User) -> UserDbEntity
) : ReadRepository<User>, WriteRepository<User> {

    override suspend fun getAll(): Either<DomainError, List<User>> =
        userDao.getAll().map(toDomain).right()

    override suspend fun saveAll(items: List<User>) {
        userDao.insertAll(items.map { toDb(it) })
    }
}