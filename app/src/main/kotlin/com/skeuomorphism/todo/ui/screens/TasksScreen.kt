package com.skeuomorphism.todo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skeuomorphism.todo.ui.components.SkeuomorphicButton
import com.skeuomorphism.todo.ui.components.Task
import com.skeuomorphism.todo.ui.components.TaskCard
import com.skeuomorphism.todo.ui.theme.BackgroundDark
import com.skeuomorphism.todo.ui.theme.MutedGreen
import com.skeuomorphism.todo.ui.theme.TextPrimary
import com.skeuomorphism.todo.ui.theme.TextSecondary

data class TaskFilter(
    val label: String,
    val type: String
)

@Composable
fun TasksScreen(
    tasks: List<Task> = emptyList(),
    onCompleteTask: (Task) -> Unit = {},
    onAddTask: () -> Unit = {},
    onFilterChange: (String) -> Unit = {}
) {
    val filters = listOf(
        TaskFilter("All", "all"),
        TaskFilter("Active", "active"),
        TaskFilter("Completed", "completed")
    )
    
    val selectedFilter = remember { mutableStateOf("all") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "TASKS",
                    color = TextPrimary,
                    fontSize = 24.sp,
                    letterSpacing = 0.1.sp
                )
                
                Text(
                    text = "${tasks.count { !it.isCompleted }} remaining",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_add),
                contentDescription = "Add Task",
                tint = MutedGreen,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onAddTask() }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Filters
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filters.forEach { filter ->
                val isSelected = selectedFilter.value == filter.type
                
                Box(
                    modifier = Modifier
                        .height(32.dp)
                        .background(
                            color = if (isSelected) MutedGreen else BackgroundDark,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            selectedFilter.value = filter.type
                            onFilterChange(filter.type)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = filter.label,
                        color = if (isSelected) BackgroundDark else TextSecondary,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Tasks list
        if (tasks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 64.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_agenda),
                        contentDescription = "No Tasks",
                        tint = TextSecondary,
                        modifier = Modifier.size(48.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "No tasks yet",
                        color = TextSecondary,
                        fontSize = 16.sp
                    )
                    
                    Text(
                        text = "Add one to get started",
                        color = TextSecondary.copy(alpha = 0.7f),
                        fontSize = 12.sp
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tasks) { task ->
                    TaskCard(
                        task = task,
                        onComplete = onCompleteTask
                    )
                }
            }
        }
        
        // Add task button (floating)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            SkeuomorphicButton(
                onClick = onAddTask,
                text = "ADD TASK",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(48.dp)
            )
        }
    }
}

@Composable
fun AddTaskDialog(
    onDismiss: () -> Unit,
    onSave: (String, String, Priority) -> Unit
) {
    // Simple dialog implementation
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .background(BackgroundDark)
    ) {
        Text(
            text = "Add New Task",
            color = TextPrimary,
            fontSize = 20.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Form fields would go here
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            SkeuomorphicButton(
                onClick = onDismiss,
                text = "CANCEL",
                modifier = Modifier
                    .height(40.dp)
                    .padding(end = 8.dp)
            )
            
            SkeuomorphicButton(
                onClick = { onSave("New Task", "09:00", Priority.MEDIUM) },
                text = "SAVE",
                modifier = Modifier.height(40.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TasksScreenPreview() {
    val sampleTasks = listOf(
        Task(1, "Morning Workout", "07:30", Priority.HIGH),
        Task(2, "Read 20 pages", "09:00", Priority.MEDIUM),
        Task(3, "Team Meeting", "10:00", Priority.HIGH),
        Task(4, "Lunch Break", "12:00", Priority.LOW),
        Task(5, "Code Review", "14:00", Priority.MEDIUM, true)
    )
    
    TasksScreen(
        tasks = sampleTasks,
        onCompleteTask = {}
    )
}

@Preview(showBackground = true)
@Composable
fun TasksScreenEmptyPreview() {
    TasksScreen(
        tasks = emptyList(),
        onCompleteTask = {}
    )
}

// Extension for clickable
fun Modifier.clickable(onClick: () -> Unit): Modifier {
    return this.then(
        androidx.compose.foundation.clickable(
            interactionSource = androidx.compose.foundation.interaction.MutableInteractionSource(),
            indication = null,
            onClick = onClick
        )
    )
}
