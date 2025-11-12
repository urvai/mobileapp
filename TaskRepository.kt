package com.focusmate.data.repository

import com.focusmate.data.dao.TaskDao
import com.focusmate.data.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(task: Task) = taskDao.insertTask(task)

    suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
}
