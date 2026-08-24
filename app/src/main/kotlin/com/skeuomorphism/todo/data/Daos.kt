package com.skeuomorphism.todo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY createdAt DESC")
    suspend fun getAllTasks(): List<TaskEntity>
    
    @Query("SELECT * FROM tasks WHERE isCompleted = 0 ORDER BY createdAt DESC")
    suspend fun getActiveTasks(): List<TaskEntity>
    
    @Query("SELECT * FROM tasks WHERE isCompleted = 1 ORDER BY completedAt DESC")
    suspend fun getCompletedTasks(): List<TaskEntity>
    
    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: Int): TaskEntity?
    
    @Insert
    suspend fun insertTask(task: TaskEntity): Long
    
    @Update
    suspend fun updateTask(task: TaskEntity): Int
    
    @Delete
    suspend fun deleteTask(task: TaskEntity): Int
    
    @Query("SELECT COUNT(*) FROM tasks WHERE isCompleted = 1")
    suspend fun getCompletedCount(): Int
    
    @Query("SELECT COUNT(*) FROM tasks")
    suspend fun getTotalCount(): Int
}

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits ORDER BY createdAt DESC")
    suspend fun getAllHabits(): List<HabitEntity>
    
    @Query("SELECT * FROM habits WHERE isCompletedToday = 1")
    suspend fun getCompletedTodayHabits(): List<HabitEntity>
    
    @Query("SELECT * FROM habits WHERE id = :id")
    suspend fun getHabitById(id: Int): HabitEntity?
    
    @Insert
    suspend fun insertHabit(habit: HabitEntity): Long
    
    @Update
    suspend fun updateHabit(habit: HabitEntity): Int
    
    @Delete
    suspend fun deleteHabit(habit: HabitEntity): Int
    
    @Query("UPDATE habits SET isCompletedToday = 0")
    suspend fun resetDailyCompletion()
    
    @Query("SELECT SUM(currentStreak) FROM habits")
    suspend fun getTotalStreak(): Int
    
    @Query("SELECT AVG(currentStreak) FROM habits")
    suspend fun getAverageStreak(): Double
}

@Dao
interface RecoveryDao {
    @Query("SELECT * FROM recovery ORDER BY date DESC LIMIT 1")
    suspend fun getLatestRecovery(): RecoveryEntity?
    
    @Query("SELECT * FROM recovery WHERE date = :date")
    suspend fun getRecoveryByDate(date: String): RecoveryEntity?
    
    @Query("SELECT * FROM recovery ORDER BY date DESC")
    suspend fun getAllRecovery(): List<RecoveryEntity>
    
    @Insert
    suspend fun insertRecovery(recovery: RecoveryEntity): Long
    
    @Update
    suspend fun updateRecovery(recovery: RecoveryEntity): Int
    
    @Query("SELECT MAX(day) FROM recovery")
    suspend fun getMaxDay(): Int?
    
    @Query("SELECT COUNT(*) FROM recovery WHERE isOnTrack = 1")
    suspend fun getOnTrackDays(): Int
}

@Dao
interface UrgeDao {
    @Query("SELECT * FROM urges ORDER BY timestamp DESC")
    suspend fun getAllUrges(): List<UrgeEntity>
    
    @Query("SELECT * FROM urges WHERE resisted = 1")
    suspend fun getResistedUrges(): List<UrgeEntity>
    
    @Query("SELECT * FROM urges WHERE id = :id")
    suspend fun getUrgeById(id: Int): UrgeEntity?
    
    @Insert
    suspend fun insertUrge(urge: UrgeEntity): Long
    
    @Update
    suspend fun updateUrge(urge: UrgeEntity): Int
    
    @Delete
    suspend fun deleteUrge(urge: UrgeEntity): Int
    
    @Query("SELECT COUNT(*) FROM urges WHERE resisted = 1")
    suspend fun getResistedCount(): Int
    
    @Query("SELECT AVG(duration) FROM urges")
    suspend fun getAverageDuration(): Double
}

@Dao
interface XPDao {
    @Query("SELECT * FROM xp ORDER BY date DESC LIMIT 1")
    suspend fun getLatestXP(): XPEntity?
    
    @Query("SELECT * FROM xp WHERE date = :date")
    suspend fun getXPByDate(date: String): XPEntity?
    
    @Insert
    suspend fun insertXP(xp: XPEntity): Long
    
    @Update
    suspend fun updateXP(xp: XPEntity): Int
}
