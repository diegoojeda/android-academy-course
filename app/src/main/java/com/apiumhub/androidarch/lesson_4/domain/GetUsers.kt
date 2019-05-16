package com.apiumhub.androidarch.lesson_4.domain

import arrow.core.Either
import arrow.core.Option
import com.apiumhub.androidarch.lesson_6.common.User

class GetUsers(
    private val getFromNetwork: suspend () -> Either<Error, List<User>>,
    private val getFromDb: suspend () -> Option<List<User>>,
    private val storeOnDb: (List<User>) -> Unit
) {
    suspend fun execute(): List<User> {
        getFromDb().fold({
            return userNotInDb()
        }, {
            return it
        })
    }

    private suspend fun userNotInDb(): List<User> {
        getFromNetwork().fold({
            throw it
        }, {
            storeOnDb(it)
            return it
        })
    }
}