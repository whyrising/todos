package com.github.whyrising.todos.view

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.whyrising.todos.R
import com.github.whyrising.todos.gateway.GatewayMock
import com.github.whyrising.todos.presentation.UsersViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main)

object VmFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(UsersViewModel::class.java) -> {
            UsersViewModel(GatewayMock) as T
        }
        else -> throw IllegalArgumentException("Unknown ViewModel!")
    }
}
