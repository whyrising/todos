package com.github.whyrising.todos.core

interface GatewayProvider {
    fun userGateway(): UsersGateway
}
