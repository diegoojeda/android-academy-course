package com.apiumhub.androidarch.lesson_3

import arrow.core.Either
import kotlinx.coroutines.delay
import java.util.*
import kotlin.random.Random

class NetworkClient {
    suspend fun getUsers(): Either<Error, List<User>> {
        delay(1000)
        if (Random.nextBoolean()) {
            return Either.left(Error())
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
