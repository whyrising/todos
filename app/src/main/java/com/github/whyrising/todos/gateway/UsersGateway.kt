package com.github.whyrising.todos.gateway

import com.github.whyrising.todos.presentation.User

interface UsersGateway {
    suspend fun fetchUsers(): List<User>
}
