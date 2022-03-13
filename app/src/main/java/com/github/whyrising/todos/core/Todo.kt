package com.github.whyrising.todos.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

private const val completed = "completed"

@Serializable
@Entity
data class Todo @OptIn(ExperimentalSerializationApi::class) constructor(
    @PrimaryKey val id: String,
    val title: String,
    @ColumnInfo(name = completed)
    @JsonNames(completed) val isCompleted: Boolean,
    val userId: String
)
