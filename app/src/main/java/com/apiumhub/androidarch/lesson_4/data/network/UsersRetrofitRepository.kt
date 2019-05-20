package com.apiumhub.androidarch.lesson_4.data.network

import arrow.core.Either
import arrow.core.right
import com.apiumhub.androidarch.lesson_4.data.network.dto.UserNetworkDto
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import com.apiumhub.androidarch.lesson_4.domain.exception.DomainError
import com.apiumhub.androidarch.lesson_4.domain.exception.InvalidUserIdException
import com.apiumhub.androidarch.lesson_4.domain.repositories.ReadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRetrofitRepository(
    private val usersApi: UsersApi,
    private val mapper: (UserNetworkDto) -> User
) : ReadRepository<User> {
    override suspend fun getAll(): Either<DomainError, List<User>> =
        withContext(Dispatchers.IO) {
            usersApi
                .getUsersListAsync()
                .await()
                .filterMapInternal(mapper)
                .right()
        }

    /*
    Utility method that extends an Iterable of UserNetworkDto, and returns a list containing the result of mapping each
    item from the original list applying the transformation supplied, except for those that throw an InvalidUserIdException
     */
    private fun Iterable<UserNetworkDto>.filterMapInternal(transform: (UserNetworkDto) -> User): List<User> {
        return this.fold(listOf()) { acc, next ->
            try {
                return@fold acc.plus(transform(next))
            } catch (ex: InvalidUserIdException) {
                return@fold acc
            }
        }
    }
}