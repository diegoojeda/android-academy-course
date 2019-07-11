package com.apiumhub.androidarch.lesson_4.data.network.dto

import com.apiumhub.androidarch.lesson_4.domain.entity.User

data class UserNetworkDto(
    val id: String,
    val alias: String,
    val avatar: String
) {
    fun toDomain(): User = User(id, alias, avatar)
}