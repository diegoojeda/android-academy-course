package com.apiumhub.androidarch.lesson_4.domain

import arrow.core.Either
import com.apiumhub.androidarch.lesson_4.domain.exception.DomainError
import com.apiumhub.androidarch.lesson_6.common.User

class GetUsers(
    private val getFromNetwork: suspend () -> Either<DomainError, List<User>>,
    private val getFromDb: suspend () -> Either<DomainError, List<User>>,
    private val storeOnDb: (List<User>) -> Unit
) {
    suspend fun execute(): List<User> {
        TODO()
    }
}