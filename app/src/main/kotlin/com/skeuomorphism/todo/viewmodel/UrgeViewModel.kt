package com.skeuomorphism.todo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skeuomorphism.todo.data.UrgeEntity
import com.skeuomorphism.todo.data.UrgeDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UrgeViewModel(private val urgeDao: UrgeDao) : ViewModel() {
    private val _urges = MutableStateFlow<List<UrgeEntity>>(emptyList())
    val urges: StateFlow<List<UrgeEntity>> = _urges.asStateFlow()
    
    private val _resistedCount = MutableStateFlow(0)
    val resistedCount: StateFlow<Int> = _resistedCount.asStateFlow()
    
    private val _averageDuration = MutableStateFlow(0.0)
    val averageDuration: StateFlow<Double> = _averageDuration.asStateFlow()
    
    private val _isTimerRunning = MutableStateFlow(false)
    val isTimerRunning: StateFlow<Boolean> = _isTimerRunning.asStateFlow()
    
    private val _timerProgress = MutableStateFlow(0f)
    val timerProgress: StateFlow<Float> = _timerProgress.asStateFlow()
    
    init {
        loadUrges()
    }
    
    private fun loadUrges() {
        viewModelScope.launch {
            _urges.value = urgeDao.getAllUrges()
            _resistedCount.value = urgeDao.getResistedCount()
            _averageDuration.value = urgeDao.getAverageDuration()
        }
    }
    
    fun startUrgeTimer() {
        viewModelScope.launch {
            _isTimerRunning.value = true
            _timerProgress.value = 0f
            
            // Simulate timer
            for (i in 0..600 step 10) {
                if (!_isTimerRunning.value) break
                _timerProgress.value = i.toFloat() / 600f
                kotlinx.coroutines.delay(1000)
            }
            
            if (_isTimerRunning.value) {
                _isTimerRunning.value = false
                // Timer completed - urge resisted
                recordUrge(resisted = true, duration = 600)
            }
        }
    }
    
    fun cancelUrgeTimer() {
        _isTimerRunning.value = false
    }
    
    fun recordUrge(resisted: Boolean, duration: Int, notes: String = "") {
        viewModelScope.launch {
            val urge = UrgeEntity(
                timestamp = System.currentTimeMillis(),
                duration = duration,
                resisted = resisted,
                notes = notes
            )
            urgeDao.insertUrge(urge)
            loadUrges()
        }
    }
    
    fun updateUrge(urge: UrgeEntity) {
        viewModelScope.launch {
            urgeDao.updateUrge(urge)
            loadUrges()
        }
    }
    
    fun deleteUrge(urgeId: Int) {
        viewModelScope.launch {
            val urge = urgeDao.getUrgeById(urgeId)
            urge?.let {
                urgeDao.deleteUrge(it)
                loadUrges()
            }
        }
    }
}
