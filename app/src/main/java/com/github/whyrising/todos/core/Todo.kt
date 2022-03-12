package com.github.whyrising.todos.core

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class Todo @OptIn(ExperimentalSerializationApi::class) constructor(
    val id: String,
    val title: String,
    @JsonNames("completed") val isCompleted: Boolean
)
