package com.apiumhub.androidarch.lesson_4.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apiumhub.androidarch.lesson_4.domain.entity.User

@Entity(tableName = "User")
data class UserDbEntity(
    @PrimaryKey var id: String,
    var alias: String,
    var avatar: String
) {
    fun toDomain(): User = User(id, alias, avatar)

    companion object {
        fun fromDomain(user: User): UserDbEntity = UserDbEntity(user.id, user.alias, user.avatar)
    }
}