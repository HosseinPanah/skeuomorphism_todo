package com.skeuomorphism.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String = "",
    val time: String = "",
    val priority: String = "NONE",
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val completedAt: Long? = null
)

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String = "",
    val currentStreak: Int = 0,
    val maxStreak: Int = 0,
    val isCompletedToday: Boolean = false,
    val lastCompletedAt: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "recovery")
data class RecoveryEntity(
    @PrimaryKey val date: String, // YYYY-MM-DD format
    val day: Int,
    val isOnTrack: Boolean = true,
    val notes: String = ""
)

@Entity(tableName = "urges")
data class UrgeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val duration: Int = 0, // in seconds
    val resisted: Boolean = false,
    val notes: String = ""
)

@Entity(tableName = "xp")
data class XPEntity(
    @PrimaryKey val date: String, // YYYY-MM-DD format
    val level: Int,
    val currentXP: Int,
    val maxXP: Int
)

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey val key: String,
    val value: String
)
