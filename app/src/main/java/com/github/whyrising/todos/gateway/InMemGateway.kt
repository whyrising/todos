package com.github.whyrising.todos.gateway

import com.github.whyrising.todos.core.Todo
import com.github.whyrising.todos.core.User
import com.github.whyrising.todos.core.UsersGateway
import kotlinx.coroutines.delay

object InMemGateway : UsersGateway {
    private val USERS: MutableList<User> = ArrayList()
    private val TODOS: MutableList<Todo> = ArrayList()
    private const val COUNT = 25

    init {
        for (i in 1..COUNT)
            USERS.add(createPlaceholderUser(i))

        for (i in 1..COUNT)
            TODOS.add(createPlaceholderTodo(i))
    }

    private fun createPlaceholderUser(index: Int): User = User(
        id = "$index",
        name = "John Doe",
        username = "johndoe",
        email = "johndoe@example.com"
    )

    private fun createPlaceholderTodo(index: Int): Todo = Todo(
        id = "$index",
        title = "task",
        isCompleted = true,
        userId = "1"
    )

    override suspend fun users(): List<User> {
        delay(1000)
        return USERS
    }

    override suspend fun todosBy(
        userId: String
    ): List<Todo> {
        delay(1000)
        return TODOS
    }
}
