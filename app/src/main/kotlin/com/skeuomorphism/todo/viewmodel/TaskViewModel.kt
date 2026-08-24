package com.skeuomorphism.todo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skeuomorphism.todo.data.TaskEntity
import com.skeuomorphism.todo.data.TaskDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {
    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks: StateFlow<List<TaskEntity>> = _tasks.asStateFlow()
    
    private val _completedCount = MutableStateFlow(0)
    val completedCount: StateFlow<Int> = _completedCount.asStateFlow()
    
    private val _totalCount = MutableStateFlow(0)
    val totalCount: StateFlow<Int> = _totalCount.asStateFlow()
    
    init {
        loadTasks()
    }
    
    private fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = taskDao.getAllTasks()
            _completedCount.value = taskDao.getCompletedCount()
            _totalCount.value = taskDao.getTotalCount()
        }
    }
    
    fun addTask(title: String, description: String = "", time: String = "", priority: String = "NONE") {
        viewModelScope.launch {
            val task = TaskEntity(
                title = title,
                description = description,
                time = time,
                priority = priority
            )
            taskDao.insertTask(task)
            loadTasks()
        }
    }
    
    fun completeTask(taskId: Int) {
        viewModelScope.launch {
            val task = taskDao.getTaskById(taskId)
            task?.let {
                val updatedTask = it.copy(
                    isCompleted = true,
                    completedAt = System.currentTimeMillis()
                )
                taskDao.updateTask(updatedTask)
                loadTasks()
            }
        }
    }
    
    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            taskDao.updateTask(task)
            loadTasks()
        }
    }
    
    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            val task = taskDao.getTaskById(taskId)
            task?.let {
                taskDao.deleteTask(it)
                loadTasks()
            }
        }
    }
    
    fun filterTasks(filter: String) {
        viewModelScope.launch {
            _tasks.value = when (filter) {
                "active" -> taskDao.getActiveTasks()
                "completed" -> taskDao.getCompletedTasks()
                else -> taskDao.getAllTasks()
            }
        }
    }
}
