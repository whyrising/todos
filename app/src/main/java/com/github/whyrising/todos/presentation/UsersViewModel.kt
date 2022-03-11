package com.github.whyrising.todos.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.whyrising.todos.gateway.UsersGateway

class UsersViewModel(gateway: UsersGateway) : ViewModel() {
    val users: LiveData<List<User>> by lazy {
        liveData {
            emit(gateway.fetchUsers())
        }
    }
}
