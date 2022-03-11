package com.github.whyrising.todos.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.whyrising.todos.gateway.UsersGateway
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.update

class UsersViewModel(gateway: UsersGateway) : ViewModel() {
    private var selectedUser = atomic<UserViewModel?>(null)

    val users: LiveData<List<UserViewModel>> by lazy {
        liveData {
            emit(gateway.users().map { UserViewModel(it) })
        }
    }

    val userTodos: LiveData<List<TodoViewModel>> by lazy {
        liveData {
            val selected = selectedUser.value
            var atomicityFlag = true
            while (atomicityFlag) {
                val todos = when (selected) {
                    null -> listOf()
                    else -> gateway.todosBy(selected.user).map {
                        TodoViewModel(it)
                    }
                }
                if (selectedUser.value == selected) {
                    emit(todos)
                    atomicityFlag = false
                }
            }
        }
    }

    fun setSelectedUser(userViewModel: UserViewModel) {
        selectedUser.update {
            if (it != userViewModel) userViewModel else it
        }
    }
}
