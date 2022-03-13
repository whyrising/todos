package com.github.whyrising.todos

import android.app.Application
import com.github.whyrising.todos.gateway.AppDatabase

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AppDatabase.instance(applicationContext)
    }
}
