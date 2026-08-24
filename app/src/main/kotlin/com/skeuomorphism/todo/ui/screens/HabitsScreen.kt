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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skeuomorphism.todo.ui.components.Habit
import com.skeuomorphism.todo.ui.components.HabitCard
import com.skeuomorphism.todo.ui.components.HabitCardSimple
import com.skeuomorphism.todo.ui.components.LedIndicator
import com.skeuomorphism.todo.ui.components.SkeuomorphicButton
import com.skeuomorphism.todo.ui.theme.BackgroundDark
import com.skeuomorphism.todo.ui.theme.MutedGreen
import com.skeuomorphism.todo.ui.theme.TextPrimary
import com.skeuomorphism.todo.ui.theme.TextSecondary

@Composable
fun HabitsScreen(
    habits: List<Habit> = emptyList(),
    onCompleteHabit: (Habit) -> Unit = {},
    onAddHabit: () -> Unit = {}
) {
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
                    text = "HABITS",
                    color = TextPrimary,
                    fontSize = 24.sp,
                    letterSpacing = 0.1.sp
                )
                
                Text(
                    text = "${habits.count { it.isCompletedToday }} of ${habits.size} completed today",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_add),
                contentDescription = "Add Habit",
                tint = MutedGreen,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onAddHabit() }
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Stats
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            HabitStat(
                label = "TODAY",
                value = "${habits.count { it.isCompletedToday }}",
                modifier = Modifier.weight(1f)
            )
            
            HabitStat(
                label = "STREAK",
                value = "${habits.maxOfOrNull { it.streak } ?: 0}",
                modifier = Modifier.weight(1f)
            )
            
            HabitStat(
                label = "AVG",
                value = "${habits.map { it.streak }.average().toInt()}",
                modifier = Modifier.weight(1f)
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Habits list
        if (habits.isEmpty()) {
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
                        painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                        contentDescription = "No Habits",
                        tint = TextSecondary,
                        modifier = Modifier.size(48.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "No habits tracked",
                        color = TextSecondary,
                        fontSize = 16.sp
                    )
                    
                    Text(
                        text = "Add one to build discipline",
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
                items(habits) { habit ->
                    HabitCardSimple(
                        habit = habit,
                        onComplete = onCompleteHabit
                    )
                }
            }
        }
        
        // Add habit button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            SkeuomorphicButton(
                onClick = onAddHabit,
                text = "ADD HABIT",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(48.dp)
            )
        }
    }
}

@Composable
fun HabitStat(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            color = TextSecondary,
            fontSize = 10.sp,
            letterSpacing = 0.1.sp
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = value,
            color = TextPrimary,
            fontSize = 18.sp,
            letterSpacing = 0.1.sp
        )
    }
}

@Composable
fun HabitDetailScreen(
    habit: Habit,
    onBack: () -> Unit = {},
    onComplete: () -> Unit = {}
) {
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
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_revert),
                contentDescription = "Back",
                tint = TextSecondary,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onBack() }
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            Text(
                text = habit.name,
                color = TextPrimary,
                fontSize = 20.sp,
                letterSpacing = 0.1.sp
            )
            
            Spacer(modifier = Modifier.weight(1f))
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Streak display
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "CURRENT STREAK",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    letterSpacing = 0.1.sp
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "${habit.streak} DAYS",
                    color = TextPrimary,
                    fontSize = 48.sp,
                    letterSpacing = 0.1.sp
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LedIndicator(
                        color = if (habit.isCompletedToday) com.skeuomorphism.todo.ui.theme.MutedGreen 
                                else com.skeuomorphism.todo.ui.theme.MutedRed,
                        size = 6.dp
                    )
                    
                    Spacer(modifier = Modifier.size(4.dp))
                    
                    Text(
                        text = if (habit.isCompletedToday) "COMPLETED TODAY" else "PENDING",
                        color = if (habit.isCompletedToday) MutedGreen else TextSecondary,
                        fontSize = 12.sp,
                        letterSpacing = 0.1.sp
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Progress visualization
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(30) { index ->
                val isFilled = index < habit.streak
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(
                            color = if (isFilled) MutedGreen else BackgroundDark,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(2.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = if (isFilled) MutedGreen else TextSecondary.copy(alpha = 0.3f),
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(2.dp)
                        )
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Complete button
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            SkeuomorphicButton(
                onClick = onComplete,
                text = if (habit.isCompletedToday) "ALREADY COMPLETED" else "MARK COMPLETE",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(48.dp),
                enabled = !habit.isCompletedToday
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HabitsScreenPreview() {
    val sampleHabits = listOf(
        Habit(1, "READ 20 MIN", 12, 30, true),
        Habit(2, "MEDITATE", 5, 30, false),
        Habit(3, "EXERCISE", 3, 30, false),
        Habit(4, "JOURNAL", 7, 30, false),
        Habit(5, "SLEEP 8H", 21, 30, true)
    )
    
    HabitsScreen(
        habits = sampleHabits,
        onCompleteHabit = {}
    )
}

@Preview(showBackground = true)
@Composable
fun HabitsScreenEmptyPreview() {
    HabitsScreen(
        habits = emptyList(),
        onCompleteHabit = {}
    )
}

@Preview(showBackground = true)
@Composable
fun HabitDetailScreenPreview() {
    HabitDetailScreen(
        habit = Habit(1, "READ 20 MIN", 12, 30, false)
    )
}
