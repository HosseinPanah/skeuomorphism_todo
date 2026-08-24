package com.skeuomorphism.todo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.skeuomorphism.todo.ui.components.NavItem
import com.skeuomorphism.todo.ui.components.SkeuomorphicBottomNavigation
import com.skeuomorphism.todo.ui.screens.AnalyticsScreen
import com.skeuomorphism.todo.ui.screens.HabitsScreen
import com.skeuomorphism.todo.ui.screens.HomeScreen
import com.skeuomorphism.todo.ui.screens.RecoveryScreen
import com.skeuomorphism.todo.ui.screens.TasksScreen
import com.skeuomorphism.todo.ui.theme.BackgroundDark

@Composable
fun SkeuomorphismTodoApp() {
    val selectedItem = remember { mutableStateOf(NavItem.HOME) }
    
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark),
        bottomBar = {
            SkeuomorphicBottomNavigation(
                selectedItem = selectedItem.value,
                onItemSelected = { item ->
                    selectedItem.value = item
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (selectedItem.value) {
                    NavItem.HOME -> {
                        HomeScreen(
                            progress = 0.72f,
                            tasksCompleted = 3,
                            totalTasks = 5,
                            onAddTask = { selectedItem.value = NavItem.TASKS },
                            onViewTasks = { selectedItem.value = NavItem.TASKS }
                        )
                    }
                    NavItem.TASKS -> {
                        val sampleTasks = listOf(
                            com.skeuomorphism.todo.ui.components.Task(1, "Morning Workout", "07:30", com.skeuomorphism.todo.ui.components.Priority.HIGH),
                            com.skeuomorphism.todo.ui.components.Task(2, "Read 20 pages", "09:00", com.skeuomorphism.todo.ui.components.Priority.MEDIUM),
                            com.skeuomorphism.todo.ui.components.Task(3, "Team Meeting", "10:00", com.skeuomorphism.todo.ui.components.Priority.HIGH),
                            com.skeuomorphism.todo.ui.components.Task(4, "Lunch Break", "12:00", com.skeuomorphism.todo.ui.components.Priority.LOW),
                            com.skeuomorphism.todo.ui.components.Task(5, "Code Review", "14:00", com.skeuomorphism.todo.ui.components.Priority.MEDIUM, true)
                        )
                        
                        TasksScreen(
                            tasks = sampleTasks,
                            onCompleteTask = { task ->
                                // Handle task completion
                            },
                            onAddTask = {},
                            onFilterChange = {}
                        )
                    }
                    NavItem.HABITS -> {
                        val sampleHabits = listOf(
                            com.skeuomorphism.todo.ui.components.Habit(1, "READ 20 MIN", 12, 30, true),
                            com.skeuomorphism.todo.ui.components.Habit(2, "MEDITATE", 5, 30, false),
                            com.skeuomorphism.todo.ui.components.Habit(3, "EXERCISE", 3, 30, false),
                            com.skeuomorphism.todo.ui.components.Habit(4, "JOURNAL", 7, 30, false),
                            com.skeuomorphism.todo.ui.components.Habit(5, "SLEEP 8H", 21, 30, true)
                        )
                        
                        HabitsScreen(
                            habits = sampleHabits,
                            onCompleteHabit = { habit ->
                                // Handle habit completion
                            },
                            onAddHabit = {}
                        )
                    }
                    NavItem.RECOVERY -> {
                        RecoveryScreen(
                            day = 14,
                            isOnTrack = true,
                            onReset = {},
                            onUrge = {},
                            onRelapse = {}
                        )
                    }
                    NavItem.INSIGHTS -> {
                        AnalyticsScreen(
                            tasksCompleted = 24,
                            habitsCompleted = 18,
                            recoveryDays = 14,
                            urgesResisted = 5,
                            level = 12,
                            currentXP = 1240,
                            maxXP = 1500
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SkeuomorphismTodoAppPreview() {
    AppTheme {
        SkeuomorphismTodoApp()
    }
}
