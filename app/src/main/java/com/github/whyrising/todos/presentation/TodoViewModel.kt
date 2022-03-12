package com.github.whyrising.todos.presentation

import com.github.whyrising.todos.core.Todo

data class TodoViewModel(private val value: Todo) {
    val id: String = value.id
    val title: String = value.title
    val isDone: Boolean = value.isCompleted
}
