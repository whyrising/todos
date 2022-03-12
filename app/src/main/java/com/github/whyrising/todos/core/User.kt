package com.github.whyrising.todos.core

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val username: String,
    val email: String
)
