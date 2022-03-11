package com.github.whyrising.todos.presentation

data class User(
    val id: String,
    val name: String,
    val username: String,
    val email: String
) {
    override fun toString(): String = name
}
