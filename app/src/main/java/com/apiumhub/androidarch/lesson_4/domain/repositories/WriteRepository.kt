package com.apiumhub.androidarch.lesson_4.domain.repositories

interface WriteRepository<T> {
    suspend fun saveAll(items: List<T>)
}