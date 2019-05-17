package com.apiumhub.androidarch.lesson_4.data.db

import com.apiumhub.androidarch.lesson_4.domain.entity.User

fun UserDbEntity.toDomain(): User = User(id, alias, avatar)

fun User.toDbEntity(): UserDbEntity = UserDbEntity(id, alias, avatar)