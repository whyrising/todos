package com.github.whyrising.todos.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class User(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "full_name") val name: String,
    val username: String,
    val email: String
)
