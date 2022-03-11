package com.github.whyrising.todos.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.whyrising.todos.R
import com.github.whyrising.todos.gateway.GatewayMock
import com.github.whyrising.todos.presentation.UsersViewModel
import com.github.whyrising.todos.view.users.UsersFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<UsersFragment>(R.id.fragment_container_view)
            }
        }
    }
}

object VmFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(UsersViewModel::class.java) -> {
            UsersViewModel(GatewayMock) as T
        }
        else -> throw IllegalArgumentException("Unknown ViewModel!")
    }
}
