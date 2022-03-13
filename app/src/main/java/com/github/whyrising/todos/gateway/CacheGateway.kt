package com.github.whyrising.todos.gateway

import androidx.room.Room
import com.github.whyrising.todos.core.Todo
import com.github.whyrising.todos.core.User
import com.github.whyrising.todos.core.UsersGateway

/**
 * The cache gateway for fetching data from [Room] database.
 */
class CacheGateway(private val db: AppDatabase) : UsersGateway {
    override suspend fun users(): List<User> = db.userDao().getAll()

    override suspend fun todosBy(userId: String): List<Todo> =
        db.todosDao().getTodosBy(userId)
}
