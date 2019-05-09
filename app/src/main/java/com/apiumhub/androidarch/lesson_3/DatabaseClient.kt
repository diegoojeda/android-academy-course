package com.apiumhub.androidarch.lesson_3

import arrow.core.Option
import kotlinx.coroutines.delay

class DatabaseClient {
    private var usersList = Option.empty<List<User>>()

    suspend fun getUsers(): Option<List<User>> {
        delay(1000)
        return usersList
    }

    fun storeUsers(users: List<User>) {
        this.usersList = Option.just(users)
    }
}