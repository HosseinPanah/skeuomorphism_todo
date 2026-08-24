package com.skeuomorphism.todo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skeuomorphism.todo.data.RecoveryEntity
import com.skeuomorphism.todo.data.RecoveryDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class RecoveryViewModel(private val recoveryDao: RecoveryDao) : ViewModel() {
    private val _currentDay = MutableStateFlow(0)
    val currentDay: StateFlow<Int> = _currentDay.asStateFlow()
    
    private val _isOnTrack = MutableStateFlow(true)
    val isOnTrack: StateFlow<Boolean> = _isOnTrack.asStateFlow()
    
    private val _maxDay = MutableStateFlow(0)
    val maxDay: StateFlow<Int> = _maxDay.asStateFlow()
    
    private val _onTrackDays = MutableStateFlow(0)
    val onTrackDays: StateFlow<Int> = _onTrackDays.asStateFlow()
    
    init {
        loadRecoveryData()
    }
    
    private fun loadRecoveryData() {
        viewModelScope.launch {
            val latest = recoveryDao.getLatestRecovery()
            val maxDay = recoveryDao.getMaxDay() ?: 0
            val onTrackDays = recoveryDao.getOnTrackDays()
            
            _maxDay.value = maxDay
            _onTrackDays.value = onTrackDays
            
            if (latest != null) {
                _currentDay.value = latest.day
                _isOnTrack.value = latest.isOnTrack
            } else {
                // Initialize new recovery
                val today = LocalDate.now().toString()
                val newDay = 1
                val newRecovery = RecoveryEntity(
                    date = today,
                    day = newDay,
                    isOnTrack = true
                )
                recoveryDao.insertRecovery(newRecovery)
                _currentDay.value = newDay
                _isOnTrack.value = true
            }
        }
    }
    
    fun incrementDay() {
        viewModelScope.launch {
            val latest = recoveryDao.getLatestRecovery()
            val today = LocalDate.now().toString()
            
            latest?.let {
                val newDay = it.day + 1
                val newRecovery = it.copy(
                    date = today,
                    day = newDay
                )
                recoveryDao.insertRecovery(newRecovery)
                _currentDay.value = newDay
            } ?: run {
                val newRecovery = RecoveryEntity(
                    date = today,
                    day = 1,
                    isOnTrack = true
                )
                recoveryDao.insertRecovery(newRecovery)
                _currentDay.value = 1
            }
        }
    }
    
    fun resetRecovery() {
        viewModelScope.launch {
            val today = LocalDate.now().toString()
            val newRecovery = RecoveryEntity(
                date = today,
                day = 1,
                isOnTrack = true
            )
            recoveryDao.insertRecovery(newRecovery)
            _currentDay.value = 1
            _isOnTrack.value = true
        }
    }
    
    fun setOnTrack(isOnTrack: Boolean) {
        viewModelScope.launch {
            val latest = recoveryDao.getLatestRecovery()
            latest?.let {
                val updated = it.copy(isOnTrack = isOnTrack)
                recoveryDao.updateRecovery(updated)
                _isOnTrack.value = isOnTrack
            }
        }
    }
    
    fun addNote(note: String) {
        viewModelScope.launch {
            val latest = recoveryDao.getLatestRecovery()
            latest?.let {
                val updated = it.copy(notes = note)
                recoveryDao.updateRecovery(updated)
            }
        }
    }
}
