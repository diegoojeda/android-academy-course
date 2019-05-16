package com.apiumhub.androidarch.lesson_4.domain.entity

import com.apiumhub.androidarch.lesson_4.domain.exception.InvalidUserIdException

data class User(
    val id: String,
    val alias: String,
    val avatar: String
) {
    private val uuidRegex =
        "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}".toRegex()

    init {
        if (!id.matches(uuidRegex)) {
            throw InvalidUserIdException()
        }
    }
}