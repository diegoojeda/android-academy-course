package com.apiumhub.androidarch.lesson_4.domain.repositories

import arrow.core.Either
import com.apiumhub.androidarch.lesson_4.domain.exception.DomainError

interface ReadRepository<T> {
    suspend fun getAll(): Either<DomainError, List<T>>
}

interface WriteRepository<T> {
    suspend fun saveAll(items: List<T>)
}