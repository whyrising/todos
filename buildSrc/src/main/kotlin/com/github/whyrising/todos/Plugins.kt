package com.github.whyrising.todos

object Plugins {
    object Navigation {
        private const val version = Libs.Androidx.Navigation.version
        val safeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
    }
}
