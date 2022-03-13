package com.github.whyrising.todos.gateway

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.whyrising.todos.core.Todo
import com.github.whyrising.todos.core.User
import com.github.whyrising.todos.core.UsersGateway
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.updateAndGet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert(onConflict = REPLACE)
    fun insertAll(users: List<User>)
}

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private val instance = atomic<AppDatabase?>(null)

        fun instance(context: Context): AppDatabase {
            return instance.updateAndGet {
                it ?: Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "todos-db"
                ).build()
            }!!
        }
    }
}

class CacheGateway(private val userDao: UserDao) : UsersGateway {
    override suspend fun users(): List<User> {
        return withContext(Dispatchers.IO) {
            userDao.getAll()
        }
    }

    override suspend fun todosBy(userId: String): List<Todo> {
        return withContext(Dispatchers.IO) {
            listOf()
        }
        TODO("Not yet implemented")
    }
}