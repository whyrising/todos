package com.github.whyrising.todos.gateway

interface UsersGateway {
    suspend fun fetchUsers(): List<User>
}
