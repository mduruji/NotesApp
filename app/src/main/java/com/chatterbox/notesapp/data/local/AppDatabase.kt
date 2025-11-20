package com.chatterbox.notesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chatterbox.notesapp.data.local.dao.TodoDao
import com.chatterbox.notesapp.data.local.entity.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
