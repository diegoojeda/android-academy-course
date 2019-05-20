package com.apiumhub.androidarch.lesson_4.domain.entity

import com.apiumhub.androidarch.lesson_4.domain.exception.InvalidUserIdException
import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.Test
import java.util.*

class UserTest {

    private val alias = "someAlias"
    private val avatar = "https://api.adorable.io/avatars/1"

    @Test
    fun shouldFailWhenIdIsNotUUID() {
        val id = "someStringNotUUID"
        assertThatExceptionOfType(InvalidUserIdException::class.java).isThrownBy { User(id, alias, avatar) }
    }

    @Test
    fun shouldNotThrowAnyExceptionWhenCreatingUserWithUUIDAsId() {
        val id = UUID.randomUUID().toString()
        assertThatCode { User(id, alias, avatar) }.doesNotThrowAnyException()
    }
}