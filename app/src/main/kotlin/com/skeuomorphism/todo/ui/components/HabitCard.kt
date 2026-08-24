package com.skeuomorphism.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skeuomorphism.todo.ui.theme.CardShape
import com.skeuomorphism.todo.ui.theme.DarkGray
import com.skeuomorphism.todo.ui.theme.MutedGreen
import com.skeuomorphism.todo.ui.theme.SurfaceInset
import com.skeuomorphism.todo.ui.theme.SurfaceRaised
import com.skeuomorphism.todo.ui.theme.TextPrimary
import com.skeuomorphism.todo.ui.theme.TextSecondary

data class Habit(
    val id: Int,
    val name: String,
    val streak: Int = 0,
    val maxStreak: Int = 30,
    val isCompletedToday: Boolean = false
)

@Composable
fun HabitCard(
    habit: Habit,
    onComplete: (Habit) -> Unit,
    modifier: Modifier = Modifier
) {
    val isCompleted = habit.isCompletedToday
    
    val cardModifier = modifier
        .height(72.dp)
        .fillMaxWidth()
        .shadow(
            elevation = if (!isCompleted) 2.dp else 0.dp,
            shape = CardShape,
            ambientColor = Color.Black.copy(alpha = 0.1f)
        )
        .background(
            color = if (isCompleted) SurfaceInset else SurfaceRaised,
            shape = CardShape
        )
        .border(
            width = 1.dp,
            color = if (isCompleted) MutedGreen.copy(alpha = 0.3f) else DarkGray,
            shape = CardShape
        )
        .clip(CardShape)
        .clickable { onComplete(habit) }
    
    Box(
        modifier = cardModifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Completion indicator
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = if (isCompleted) MutedGreen else Color.Transparent,
                        shape = CircleShape
                    )
                    .border(
                        width = 1.dp,
                        color = if (isCompleted) MutedGreen else DarkGray,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    LedIndicator(
                        color = com.skeuomorphism.todo.ui.theme.MutedGreen,
                        size = 10.dp
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color.Transparent, CircleShape)
                            .border(1.dp, DarkGray, CircleShape)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Habit info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = habit.name,
                    color = if (isCompleted) TextSecondary else TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                
                if (habit.streak > 0) {
                    Text(
                        text = "${habit.streak} DAY STREAK",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                }
            }
            
            // Progress bar
            if (habit.maxStreak > 0) {
                val progress = habit.streak.toFloat() / habit.maxStreak
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(4.dp)
                        .background(DarkGray, RoundedCornerShape(2.dp)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Box(
                        modifier = Modifier
                            .width((100.dp * progress).coerceAtMost(100.dp))
                            .height(4.dp)
                            .background(
                                color = if (isCompleted) MutedGreen else Color.LightGray,
                                shape = RoundedCornerShape(2.dp)
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun HabitCardSimple(
    habit: Habit,
    onComplete: (Habit) -> Unit,
    modifier: Modifier = Modifier
) {
    val isCompleted = habit.isCompletedToday
    
    val cardModifier = modifier
        .height(64.dp)
        .fillMaxWidth()
        .shadow(
            elevation = if (!isCompleted) 1.dp else 0.dp,
            shape = CardShape,
            ambientColor = Color.Black.copy(alpha = 0.1f)
        )
        .background(
            color = if (isCompleted) SurfaceInset else SurfaceRaised,
            shape = CardShape
        )
        .border(
            width = 1.dp,
            color = if (isCompleted) MutedGreen.copy(alpha = 0.3f) else DarkGray,
            shape = CardShape
        )
        .clip(CardShape)
        .clickable { onComplete(habit) }
    
    Box(
        modifier = cardModifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // LED indicator
            if (isCompleted) {
                LedIndicator(
                    color = com.skeuomorphism.todo.ui.theme.MutedGreen,
                    size = 8.dp
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(DarkGray, CircleShape)
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Habit name
            Text(
                text = habit.name,
                color = if (isCompleted) TextSecondary else TextPrimary,
                fontSize = 14.sp
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Streak
            if (habit.streak > 0) {
                Text(
                    text = "${habit.streak}",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HabitCardPreview() {
    HabitCard(
        habit = Habit(
            id = 1,
            name = "READ 20 MIN",
            streak = 12,
            maxStreak = 30
        ),
        onComplete = {}
    )
}

@Preview(showBackground = true)
@Composable
fun HabitCardCompletedPreview() {
    HabitCard(
        habit = Habit(
            id = 2,
            name = "MEDITATE",
            streak = 5,
            maxStreak = 30,
            isCompletedToday = true
        ),
        onComplete = {}
    )
}
