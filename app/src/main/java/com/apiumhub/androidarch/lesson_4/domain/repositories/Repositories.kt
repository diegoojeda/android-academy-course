package com.apiumhub.androidarch.lesson_4.domain.repositories

import arrow.core.Try

interface ReadRepository<T> {
    suspend fun getAll(): Try<List<T>>
}

interface WriteRepository<T> {
    suspend fun saveAll(items: List<T>)
}