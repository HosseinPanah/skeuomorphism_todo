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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skeuomorphism.todo.ui.components.LedIndicator
import com.skeuomorphism.todo.ui.components.RecoveryCard
import com.skeuomorphism.todo.ui.components.SkeuomorphicButton
import com.skeuomorphism.todo.ui.components.SkeuomorphicCard
import com.skeuomorphism.todo.ui.theme.BackgroundDark
import com.skeuomorphism.todo.ui.theme.MutedGreen
import com.skeuomorphism.todo.ui.theme.MutedRed
import com.skeuomorphism.todo.ui.theme.TextPrimary
import com.skeuomorphism.todo.ui.theme.TextSecondary

@Composable
fun RecoveryScreen(
    day: Int = 14,
    isOnTrack: Boolean = true,
    onReset: () -> Unit = {},
    onUrge: () -> Unit = {},
    onRelapse: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        // Header
        Text(
            text = "RECOVERY",
            color = TextPrimary,
            fontSize = 24.sp,
            letterSpacing = 0.1.sp
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Your journey to discipline and control",
            color = TextSecondary,
            fontSize = 14.sp
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Main recovery card
        RecoveryCard(
            recovery = com.skeuomorphism.todo.ui.components.RecoveryData(
                day = day,
                isOnTrack = isOnTrack,
                message = "$day DAYS STRONG"
            )
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Stats
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RecoveryStat(
                label = "CURRENT",
                value = "$day",
                modifier = Modifier.weight(1f)
            )
            
            RecoveryStat(
                label = "BEST",
                value = "30",
                modifier = Modifier.weight(1f)
            )
            
            RecoveryStat(
                label = "TOTAL",
                value = "180",
                modifier = Modifier.weight(1f)
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Quick actions
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SkeuomorphicButton(
                onClick = onUrge,
                text = "I HAVE AN URGE",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            )
            
            if (!isOnTrack) {
                SkeuomorphicButton(
                    onClick = onReset,
                    text = "START AGAIN",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Information
        SkeuomorphicCard(
            modifier = Modifier.fillMaxWidth(),
            isInset = true
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LedIndicator(
                        color = MutedGreen,
                        size = 6.dp
                    )
                    
                    Spacer(modifier = Modifier.size(6.dp))
                    
                    Text(
                        text = "One day at a time",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "Every day without acting on the urge strengthens your resolve.",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Composable
fun RecoveryStat(
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
fun RelapseScreen(
    onStartAgain: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        
        // Header
        Text(
            text = "RESET",
            color = TextPrimary,
            fontSize = 24.sp,
            letterSpacing = 0.1.sp
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "One setback doesn't erase your progress.",
            color = TextSecondary,
            fontSize = 14.sp
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Message
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LedIndicator(
                        color = MutedRed,
                        size = 8.dp
                    )
                    
                    Spacer(modifier = Modifier.size(8.dp))
                    
                    Text(
                        text = "It's okay. Tomorrow is a new day.",
                        color = TextPrimary,
                        fontSize = 16.sp
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        // Action button
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            SkeuomorphicButton(
                onClick = onStartAgain,
                text = "START AGAIN",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(48.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Stats
        SkeuomorphicCard(
            modifier = Modifier.fillMaxWidth(),
            isInset = true
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "YOUR PROGRESS",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    letterSpacing = 0.1.sp
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "14 DAYS ACHIEVED",
                    color = TextPrimary,
                    fontSize = 16.sp
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "Keep going. You've done this before.",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun UrgeScreen(
    onStartTimer: () -> Unit = {},
    onCancel: () -> Unit = {},
    isTimerRunning: Boolean = false,
    timerProgress: Float = 0f
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        
        // Header
        Text(
            text = "I HAVE AN URGE",
            color = TextPrimary,
            fontSize = 18.sp,
            letterSpacing = 0.1.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Message
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Take a breath.",
                    color = TextPrimary,
                    fontSize = 16.sp
                )
                
                Text(
                    text = "You don't have to act on this feeling.",
                    color = TextSecondary,
                    fontSize = 14.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Timer display
        if (isTimerRunning) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Timer progress
                    com.skeuomorphism.todo.ui.components.SkeuomorphicCircularProgress(
                        progress = timerProgress,
                        size = 80.dp,
                        strokeWidth = 4.dp,
                        showLed = false
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "${(timerProgress * 600).toInt()} SEC",
                        color = TextPrimary,
                        fontSize = 14.sp
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LedIndicator(
                        color = com.skeuomorphism.todo.ui.theme.MutedBlue,
                        size = 8.dp
                    )
                    
                    Spacer(modifier = Modifier.size(8.dp))
                    
                    Text(
                        text = "10 MIN TIMER",
                        color = TextSecondary,
                        fontSize = 14.sp
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Action buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (!isTimerRunning) {
                SkeuomorphicButton(
                    onClick = onStartTimer,
                    text = "START 10 MIN",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                )
            } else {
                SkeuomorphicButton(
                    onClick = onCancel,
                    text = "CANCEL TIMER",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                )
            }
            
            SkeuomorphicButton(
                onClick = onCancel,
                text = "I CAN RESIST",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = !isTimerRunning
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Information
        SkeuomorphicCard(
            modifier = Modifier.fillMaxWidth(),
            isInset = true
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "This feeling will pass.",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "The urge typically lasts 10-15 minutes.",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecoveryScreenPreview() {
    RecoveryScreen(
        day = 14,
        isOnTrack = true
    )
}

@Preview(showBackground = true)
@Composable
fun RelapseScreenPreview() {
    RelapseScreen()
}

@Preview(showBackground = true)
@Composable
fun UrgeScreenPreview() {
    UrgeScreen()
}

@Preview(showBackground = true)
@Composable
fun UrgeScreenRunningPreview() {
    UrgeScreen(
        isTimerRunning = true,
        timerProgress = 0.5f
    )
}
