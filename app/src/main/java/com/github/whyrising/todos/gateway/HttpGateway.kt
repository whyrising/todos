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

private const val baseAddress = "https://jsonplaceholder.typicode.com"
private const val usersApi = "/users"
private fun todosApi(userId: String) = "/todos?userId=$userId"

class HttpGateway(private val db: AppDatabase) : UsersGateway {

    private val kotlinxSerializer = KotlinxSerializer(
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    )

    private val httpClient = HttpClient(Android) {
        install(JsonFeature) {
            serializer = kotlinxSerializer
        }
    }

    override suspend fun users(): List<User> = try {
        val r = httpClient.get<List<User>>("$baseAddress$usersApi")
        db.userDao().insertAll(r)
        r
    } catch (e: UnknownHostException) {
        Log.e("Error", "$e")
        throw GatewayUnavailable()
    }

    override suspend fun todosBy(userId: String): List<Todo> = try {
        val r = httpClient.get<List<Todo>>("$baseAddress${todosApi(userId)}")
        db.todosDao().insertAll(r)
        r
    } catch (e: UnknownHostException) {
        Log.e("Error", "$e")
        throw GatewayUnavailable()
    }
}
