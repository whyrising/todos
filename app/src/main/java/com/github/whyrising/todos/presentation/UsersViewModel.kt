package com.github.whyrising.todos.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.github.whyrising.todos.gateway.UsersGateway
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.update
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UsersViewModel(private val gateway: UsersGateway) : ViewModel() {
    private val showUserTodosChannel = Channel<String>(Channel.CONFLATED)
    val showUserTodosEvents = showUserTodosChannel.receiveAsFlow()

    private val _selectedUserId = atomic<String?>(null)

    val users: LiveData<List<UserViewModel>> by lazy {
        liveData {
            emit(gateway.users().map { UserViewModel(it) })
        }
    }

    private val _userTodos = MutableLiveData<List<TodoViewModel>>(listOf())
    val userTodos: LiveData<List<TodoViewModel>> = _userTodos

    fun showUserTodos(userId: String) {
        _selectedUserId.update {
            val isNewUser = it != userId
            if (isNewUser) {
                _userTodos.postValue(listOf())
                showUserTodosChannel.trySend(userId)
            }
            userId
        }
    }

    fun fetchTodos(userId: String) {
        viewModelScope.launch {
            gateway.todosBy(userId)
                .map { TodoViewModel(it) }
                .let { todos -> _userTodos.value = todos }
        }
    }
}
