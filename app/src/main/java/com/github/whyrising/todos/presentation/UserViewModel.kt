package com.github.whyrising.todos.presentation

import com.github.whyrising.todos.gateway.User

data class UserViewModel(val user: User) {
    val id: String = user.id
    val fullName: String = user.name
    val username: String = "#${user.username}"
    val email: String = user.email
}
