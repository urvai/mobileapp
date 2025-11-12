package com.focusmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.focusmate.data.database.AppDatabase
import com.focusmate.data.repository.TaskRepository
import com.focusmate.ui.home.HomeScreen
import com.focusmate.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "focusmate-db"
        ).build()

        val repo = TaskRepository(db.taskDao())
        val viewModel = TaskViewModel(repo)

        setContent {
            HomeScreen(viewModel)
        }
    }
}
