package com.apiumhub.androidarch.lesson_4.data.network

import com.apiumhub.androidarch.lesson_4.data.network.dto.UserNetworkDto
import com.apiumhub.androidarch.lesson_4.domain.entity.User

fun UserNetworkDto.toDomain() = User(id, alias, avatar)