package com.github.whyrising.todos.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.whyrising.todos.R
import com.github.whyrising.todos.gateway.GatewayProviderImpl
import com.github.whyrising.todos.presentation.UsersViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main)

class VmFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UsersViewModel::class.java) -> {
                UsersViewModel(GatewayProviderImpl(context)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel!")
        }
    }
}
