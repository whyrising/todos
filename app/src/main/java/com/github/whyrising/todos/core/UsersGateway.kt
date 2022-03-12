package com.github.whyrising.todos.core

class GatewayUnavailable : RuntimeException()

interface UsersGateway {
    suspend fun users(): List<User>
    suspend fun todosBy(userId: String): List<Todo>
}
