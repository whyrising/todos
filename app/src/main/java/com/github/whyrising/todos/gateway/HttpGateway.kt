package com.github.whyrising.todos.gateway

import android.util.Log
import com.github.whyrising.todos.core.GatewayUnavailable
import com.github.whyrising.todos.core.Todo
import com.github.whyrising.todos.core.User
import com.github.whyrising.todos.core.UsersGateway
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import java.net.UnknownHostException

object HttpGateway : UsersGateway {
    private const val baseAddress = "https://jsonplaceholder.typicode.com"
    private const val usersApi = "/users"

    private val kotlinxSerializer = KotlinxSerializer(
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    )

    override suspend fun users(): List<User> {
        val client = HttpClient(Android) {
            install(JsonFeature) {
                serializer = kotlinxSerializer
            }
        }
        try {
            return client.get("$baseAddress$usersApi")
        } catch (e: UnknownHostException) {
            Log.e("Error", "$e")
            throw GatewayUnavailable()
        }
    }

    override suspend fun todosBy(userId: String): List<Todo> {
//        TODO("Not yet implemented")
        return listOf()
    }
}
