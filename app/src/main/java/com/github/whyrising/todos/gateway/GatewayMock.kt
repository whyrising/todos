package com.github.whyrising.todos.gateway

import kotlinx.coroutines.delay

object GatewayMock : UsersGateway {
    private val USERS: MutableList<User> = ArrayList()
    private const val COUNT = 25

    init {
        for (i in 1..COUNT)
            addItem(createPlaceholderItem(i))
    }

    private fun addItem(user: User) {
        USERS.add(user)
    }

    private fun createPlaceholderItem(index: Int): User = User(
        id = "$index",
        name = "John Doe",
        username = "johndoe",
        email = "johndoe@example.com"
    )

    override suspend fun fetchUsers(): List<User> {
        delay(1000)
        return USERS
    }
}
