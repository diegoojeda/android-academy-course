package com.apiumhub.androidarch.lesson_4.data.db

import com.apiumhub.androidarch.lesson_4.domain.repositories.ReadRepository
import com.apiumhub.androidarch.lesson_4.domain.repositories.WriteRepository
import com.apiumhub.androidarch.lesson_6.common.User

class UsersRoomRepository(
    private val userDao: UserDao,
    private val mapper: (UserDbEntity) -> User
) : ReadRepository<User>, WriteRepository<User> {
    override suspend fun getAll(): List<User> = userDao.getAll().map(mapper)

    override suspend fun saveAll(items: List<User>) {
        userDao.insertAll(items.map { UserDbEntity(it.id, it.alias, it.avatar) })
    }
}