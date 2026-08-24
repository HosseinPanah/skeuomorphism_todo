package com.skeuomorphism.todo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skeuomorphism.todo.ui.components.LedIndicator
import com.skeuomorphism.todo.ui.components.SkeuomorphicButton
import com.skeuomorphism.todo.ui.components.SkeuomorphicCircularProgress
import com.skeuomorphism.todo.ui.components.SkeuomorphicCard
import com.skeuomorphism.todo.ui.theme.BackgroundDark
import com.skeuomorphism.todo.ui.theme.CardShape
import com.skeuomorphism.todo.ui.theme.DarkGray
import com.skeuomorphism.todo.ui.theme.MutedGreen
import com.skeuomorphism.todo.ui.theme.SurfaceInset
import com.skeuomorphism.todo.ui.theme.SurfaceRaised
import com.skeuomorphism.todo.ui.theme.TextPrimary
import com.skeuomorphism.todo.ui.theme.TextSecondary
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(
    progress: Float = 0.72f,
    tasksCompleted: Int = 3,
    totalTasks: Int = 5,
    onAddTask: () -> Unit = {},
    onViewTasks: () -> Unit = {},
    onViewHabits: () -> Unit = {},
    onViewRecovery: () -> Unit = {}
) {
    val greeting = getGreeting()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Greeting
        Text(
            text = greeting,
            color = TextSecondary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.1.sp
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Welcome message
        Text(
            text = "Ready to make today better?",
            color = TextPrimary,
            fontSize = 18.sp
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Main progress panel
        SkeuomorphicCard(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(CardShape),
            isInset = true
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Today label
                Text(
                    text = "TODAY",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.1.sp
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Progress percentage
                Text(
                    text = "${(progress * 100).toInt()}%",
                    color = TextPrimary,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Circular progress
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SkeuomorphicCircularProgress(
                        progress = progress,
                        size = 120.dp,
                        strokeWidth = 4.dp,
                        trackColor = DarkGray,
                        indicatorColor = TextPrimary.copy(alpha = 0.6f),
                        completeColor = MutedGreen,
                        showLed = progress >= 1f
                    )
                    
                    // Completion LED
                    if (progress >= 1f) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(16.dp)
                                .background(SurfaceInset, android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM)
                                .clip(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM),
                            contentAlignment = Alignment.Center
                        ) {
                            LedIndicator(
                                color = MutedGreen,
                                size = 8.dp
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Task completion summary
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LedIndicator(
                        color = MutedGreen,
                        size = 6.dp
                    )
                    
                    Spacer(modifier = Modifier.size(4.dp))
                    
                    Text(
                        text = "$tasksCompleted of $totalTasks tasks completed",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Quick actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SkeuomorphicButton(
                onClick = onAddTask,
                text = "ADD TASK",
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .padding(horizontal = 4.dp)
            )
            
            SkeuomorphicButton(
                onClick = onViewTasks,
                text = "VIEW ALL",
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .padding(horizontal = 4.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Quick stats
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem(
                label = "TASKS",
                value = "$tasksCompleted",
                modifier = Modifier.weight(1f)
            )
            
            StatItem(
                label = "HABITS",
                value = "2",
                modifier = Modifier.weight(1f)
            )
            
            StatItem(
                label = "STREAK",
                value = "14",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun StatItem(
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
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun getGreeting(): String {
    val hour = LocalDateTime.now().hour
    return when (hour) {
        in 5..11 -> "GOOD MORNING"
        in 12..17 -> "GOOD AFTERNOON"
        else -> "GOOD EVENING"
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        progress = 0.72f,
        tasksCompleted = 3,
        totalTasks = 5
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenCompletePreview() {
    HomeScreen(
        progress = 1f,
        tasksCompleted = 5,
        totalTasks = 5
    )
}
