package com.github.whyrising.todos.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.github.whyrising.todos.core.GatewayProvider
import com.github.whyrising.todos.core.GatewayUnavailable
import com.github.whyrising.todos.core.UsersGateway
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.update
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UsersViewModel(
    private val gatewayProvider: GatewayProvider
) : ViewModel() {
    private val gateway: UsersGateway
        get() = gatewayProvider.userGateway()

    private val showUserTodosChannel =
        Channel<Pair<String, Boolean>>(Channel.CONFLATED)
    val showUserTodosEvents = showUserTodosChannel.receiveAsFlow()

    private val _selectedUserId = atomic<String?>(null)

    val users: LiveData<List<UserViewModel>> by lazy {
        liveData {
            val users: List<UserViewModel> =
                try {
                    gateway.users().map { UserViewModel(it) }
                } catch (e: GatewayUnavailable) {
                    // TODO: Notify user no connection
                    listOf()
                }
            emit(users)
        }
    }

    private val _userTodos = MutableLiveData<List<TodoViewModel>>(listOf())
    val userTodos: LiveData<List<TodoViewModel>> = _userTodos

    fun showUserTodos(userId: String) {
        _selectedUserId.update {
            val isNewUser = it != userId
            if (isNewUser) _userTodos.postValue(listOf())
            showUserTodosChannel.trySend(userId to isNewUser)
            userId
        }
    }

    fun fetchTodos(userId: String) {
        viewModelScope.launch {
            val todos = try {
                gateway.todosBy(userId).map { TodoViewModel(it) }
            } catch (e: GatewayUnavailable) {
                // TODO: Notify user no connection
                listOf()
            }
            delay(150)
            _userTodos.value = todos
        }
    }
}
