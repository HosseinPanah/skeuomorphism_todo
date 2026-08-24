package com.skeuomorphism.todo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skeuomorphism.todo.data.XPEntity
import com.skeuomorphism.todo.data.XPDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class XPViewModel(private val xpDao: XPDao) : ViewModel() {
    private val _level = MutableStateFlow(1)
    val level: StateFlow<Int> = _level.asStateFlow()
    
    private val _currentXP = MutableStateFlow(0)
    val currentXP: StateFlow<Int> = _currentXP.asStateFlow()
    
    private val _maxXP = MutableStateFlow(500)
    val maxXP: StateFlow<Int> = _maxXP.asStateFlow()
    
    private val _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> = _progress.asStateFlow()
    
    init {
        loadXP()
    }
    
    private fun loadXP() {
        viewModelScope.launch {
            val latest = xpDao.getLatestXP()
            
            if (latest != null) {
                _level.value = latest.level
                _currentXP.value = latest.currentXP
                _maxXP.value = latest.maxXP
                _progress.value = latest.currentXP.toFloat() / latest.maxXP
            } else {
                // Initialize new XP
                val today = LocalDate.now().toString()
                val newXP = XPEntity(
                    date = today,
                    level = 1,
                    currentXP = 0,
                    maxXP = 500
                )
                xpDao.insertXP(newXP)
                _level.value = 1
                _currentXP.value = 0
                _maxXP.value = 500
                _progress.value = 0f
            }
        }
    }
    
    fun addXP(amount: Int) {
        viewModelScope.launch {
            val latest = xpDao.getLatestXP()
            latest?.let {
                var newXP = it.currentXP + amount
                var newLevel = it.level
                var newMaxXP = it.maxXP
                
                // Check for level up
                if (newXP >= newMaxXP) {
                    newLevel++
                    newXP = 0
                    newMaxXP = newMaxXP + 200 // Increase max XP for next level
                }
                
                val today = LocalDate.now().toString()
                val updatedXP = it.copy(
                    date = today,
                    level = newLevel,
                    currentXP = newXP,
                    maxXP = newMaxXP
                )
                xpDao.updateXP(updatedXP)
                
                _level.value = newLevel
                _currentXP.value = newXP
                _maxXP.value = newMaxXP
                _progress.value = newXP.toFloat() / newMaxXP
            }
        }
    }
    
    fun setXP(level: Int, currentXP: Int, maxXP: Int) {
        viewModelScope.launch {
            val today = LocalDate.now().toString()
            val xp = XPEntity(
                date = today,
                level = level,
                currentXP = currentXP,
                maxXP = maxXP
            )
            xpDao.insertXP(xp)
            
            _level.value = level
            _currentXP.value = currentXP
            _maxXP.value = maxXP
            _progress.value = currentXP.toFloat() / maxXP
        }
    }
}
