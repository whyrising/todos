package com.github.whyrising.todos.view.placeholder

import com.github.whyrising.todos.vm.User

object PlaceholderContent {
    val USERS: MutableList<User> = ArrayList()
    private const val COUNT = 25

    init {
        for (i in 1..COUNT)
            addItem(createPlaceholderItem())
    }

    private fun addItem(user: User) {
        USERS.add(user)
    }

    private fun createPlaceholderItem(): User = User(
        name = "John Doe",
        username = "#johndoe",
        email = "johndoe@example.com"
    )
}
