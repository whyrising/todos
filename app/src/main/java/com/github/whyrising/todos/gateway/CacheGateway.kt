package com.github.whyrising.todos.gateway

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Transaction
import com.github.whyrising.todos.core.Todo
import com.github.whyrising.todos.core.User
import com.github.whyrising.todos.core.UsersGateway
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.updateAndGet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Insert(onConflict = REPLACE)
    fun insertAll(users: List<User>)
}

@Dao
interface TodoDao {
    @Transaction
    @Query(
        "SELECT * FROM Todo " +
            "INNER JOIN user ON user.id = todo.userId " +
            "WHERE user.id= :userId"
    )
    fun getTodosBy(userId: String): List<Todo>

    @Insert(onConflict = REPLACE)
    fun insertAll(todo: List<Todo>)
}

@Database(entities = [User::class, Todo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun todosDao(): TodoDao

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

class CacheGateway(private val db: AppDatabase) : UsersGateway {
    override suspend fun users(): List<User> {
        return withContext(Dispatchers.IO) {
            db.userDao().getAll()
        }
    }

    override suspend fun todosBy(userId: String): List<Todo> {
        return withContext(Dispatchers.IO) {
            db.todosDao().getTodosBy(userId)
        }
    }
}
