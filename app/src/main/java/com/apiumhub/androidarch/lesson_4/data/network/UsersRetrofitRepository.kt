package com.apiumhub.androidarch.lesson_4.data.network

import arrow.core.Try
import com.apiumhub.androidarch.lesson_4.data.network.dto.UserNetworkDto
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import com.apiumhub.androidarch.lesson_4.domain.exception.InvalidUserIdException
import com.apiumhub.androidarch.lesson_4.domain.repositories.ReadRepository

class UsersRetrofitRepository(
    private val usersApi: UsersApi
) : ReadRepository<User> {
    override suspend fun getAll(): Try<List<User>> {
        val fromNetwork = usersApi.getUsersList()
        val transformed = Try.just(fromNetwork.fold(listOf<User>()) { acc, userNetworkDto ->
            try {
                acc + userNetworkDto.toDomain()
            } catch (ex: InvalidUserIdException){
                acc
            }
        })
        println(fromNetwork.size)
        transformed.map {
            println(it.size)
        }
        return transformed
    }

    /*
    Utility method that extends an Iterable of UserNetworkDto, and returns a list containing the result of mapping each
    item from the original list applying the transformation supplied, except for those that throw an InvalidUserIdException
     */
    private fun Iterable<UserNetworkDto>.filterMapInternal(transform: (UserNetworkDto) -> User): List<User> {
        TODO()
    }
}