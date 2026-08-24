package com.skeuomorphism.todo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skeuomorphism.todo.data.HabitEntity
import com.skeuomorphism.todo.data.HabitDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class HabitViewModel(private val habitDao: HabitDao) : ViewModel() {
    private val _habits = MutableStateFlow<List<HabitEntity>>(emptyList())
    val habits: StateFlow<List<HabitEntity>> = _habits.asStateFlow()
    
    private val _completedTodayCount = MutableStateFlow(0)
    val completedTodayCount: StateFlow<Int> = _completedTodayCount.asStateFlow()
    
    private val _totalStreak = MutableStateFlow(0)
    val totalStreak: StateFlow<Int> = _totalStreak.asStateFlow()
    
    private val _averageStreak = MutableStateFlow(0.0)
    val averageStreak: StateFlow<Double> = _averageStreak.asStateFlow()
    
    init {
        loadHabits()
    }
    
    private fun loadHabits() {
        viewModelScope.launch {
            _habits.value = habitDao.getAllHabits()
            _completedTodayCount.value = habitDao.getCompletedTodayHabits().size
            _totalStreak.value = habitDao.getTotalStreak()
            _averageStreak.value = habitDao.getAverageStreak()
        }
    }
    
    fun addHabit(name: String, description: String = "") {
        viewModelScope.launch {
            val habit = HabitEntity(
                name = name,
                description = description
            )
            habitDao.insertHabit(habit)
            loadHabits()
        }
    }
    
    fun completeHabit(habitId: Int) {
        viewModelScope.launch {
            val habit = habitDao.getHabitById(habitId)
            habit?.let {
                val today = LocalDate.now().toString()
                val lastCompleted = it.lastCompletedAt
                val lastDate = if (lastCompleted != null) {
                    LocalDate.parse(java.time.Instant.ofEpochMilli(lastCompleted).atZone(java.time.ZoneId.systemDefault()).toLocalDate().toString())
                } else {
                    null
                }
                
                val newStreak = if (lastDate == null || 
                    LocalDate.parse(today).isAfter(lastDate.plusDays(1))) {
                    1
                } else if (LocalDate.parse(today) == lastDate.plusDays(1)) {
                    it.currentStreak + 1
                } else {
                    1
                }
                
                val updatedHabit = it.copy(
                    currentStreak = newStreak,
                    maxStreak = maxOf(it.maxStreak, newStreak),
                    isCompletedToday = true,
                    lastCompletedAt = System.currentTimeMillis()
                )
                habitDao.updateHabit(updatedHabit)
                loadHabits()
            }
        }
    }
    
    fun updateHabit(habit: HabitEntity) {
        viewModelScope.launch {
            habitDao.updateHabit(habit)
            loadHabits()
        }
    }
    
    fun deleteHabit(habitId: Int) {
        viewModelScope.launch {
            val habit = habitDao.getHabitById(habitId)
            habit?.let {
                habitDao.deleteHabit(it)
                loadHabits()
            }
        }
    }
    
    fun resetDailyCompletion() {
        viewModelScope.launch {
            habitDao.resetDailyCompletion()
            loadHabits()
        }
    }
}
