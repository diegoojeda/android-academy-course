package com.apiumhub.androidarch.lesson_5.common

import arrow.core.Either
import com.apiumhub.androidarch.lesson_4.domain.entity.User
import kotlinx.coroutines.delay
import java.util.*
import kotlin.math.absoluteValue
import kotlin.random.Random

class NetworkClient {
    suspend fun getUsers(): Either<Error, List<User>> {
        delay(1000)
        if (Random.nextInt().absoluteValue % 100 < 10) {
            return Either.left(InternalServerError())
        }
        return Either.right(
            listOf(
                User(
                    UUID.randomUUID().toString(),
                    "User ${Random.nextInt()}",
                    "https://api.adorable.io/avatars/${Random.nextInt()}"
                ),
                User(
                    UUID.randomUUID().toString(),
                    "User ${Random.nextInt()}",
                    "https://api.adorable.io/avatars/${Random.nextInt()}"
                ),
                User(
                    UUID.randomUUID().toString(),
                    "User ${Random.nextInt()}",
                    "https://api.adorable.io/avatars/${Random.nextInt()}"
                ),
                User(
                    UUID.randomUUID().toString(),
                    "User ${Random.nextInt()}",
                    "https://api.adorable.io/avatars/${Random.nextInt()}"
                ),
                User(
                    UUID.randomUUID().toString(),
                    "User ${Random.nextInt()}",
                    "https://api.adorable.io/avatars/${Random.nextInt()}"
                ),
                User(
                    UUID.randomUUID().toString(),
                    "User ${Random.nextInt()}",
                    "https://api.adorable.io/avatars/${Random.nextInt()}"
                )
            )
        )
    }
}

class InternalServerError: Error()