package com.focusmate.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.focusmate.data.dao.TaskDao
import com.focusmate.data.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
