package com.github.whyrising.todos.gateway

interface UsersGateway {
    suspend fun users(): List<User>
    suspend fun todosBy(userId: String): List<Todo>
}
