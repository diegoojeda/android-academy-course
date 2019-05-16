package com.apiumhub.androidarch.lesson_4.domain.repositories

interface ReadRepository<T> {
    suspend fun getAll(): List<T>
}