package com.skeuomorphism.todo.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skeuomorphism.todo.ui.theme.ButtonShape
import com.skeuomorphism.todo.ui.theme.CardShape
import com.skeuomorphism.todo.ui.theme.DarkGray
import com.skeuomorphism.todo.ui.theme.LightGray
import com.skeuomorphism.todo.ui.theme.MutedGreen
import com.skeuomorphism.todo.ui.theme.SurfaceInset
import com.skeuomorphism.todo.ui.theme.SurfaceRaised
import com.skeuomorphism.todo.ui.theme.TextPrimary
import com.skeuomorphism.todo.ui.theme.TextSecondary
import kotlinx.coroutines.launch

data class Task(
    val id: Int,
    val title: String,
    val time: String = "",
    val priority: Priority = Priority.NONE,
    val isCompleted: Boolean = false
)

enum class Priority {
    HIGH, MEDIUM, LOW, NONE
}

@Composable
fun TaskCard(
    task: Task,
    onComplete: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    val scale = remember { Animatable(1f) }
    val isCompleted = remember { task.isCompleted }
    
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    scope.launch {
                        scale.animateTo(0.98f, animationSpec = tween(50))
                    }
                }
                is PressInteraction.Release -> {
                    scope.launch {
                        scale.animateTo(1f, animationSpec = tween(100))
                    }
                }
                is PressInteraction.Cancel -> {
                    scope.launch {
                        scale.animateTo(1f, animationSpec = tween(100))
                    }
                }
            }
        }
    }
    
    val cardModifier = modifier
        .height(64.dp)
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
            // Completion button
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        color = if (isCompleted) MutedGreen else SurfaceRaised,
                        shape = CircleShape
                    )
                    .border(
                        width = 1.dp,
                        color = if (isCompleted) MutedGreen else DarkGray,
                        shape = CircleShape
                    )
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { onComplete(task) }
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    LedIndicator(
                        color = MutedGreen,
                        size = 8.dp
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(Color.Transparent, CircleShape)
                            .border(1.dp, LightGray, CircleShape)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Task info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = task.title,
                    color = if (isCompleted) TextSecondary else TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                
                if (task.time.isNotEmpty()) {
                    Text(
                        text = task.time,
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                }
            }
            
            // Priority indicator
            if (task.priority != Priority.NONE) {
                val priorityColor = when (task.priority) {
                    Priority.HIGH -> Color.Red.copy(alpha = 0.5f)
                    Priority.MEDIUM -> Color.Yellow.copy(alpha = 0.5f)
                    Priority.LOW -> Color.Green.copy(alpha = 0.5f)
                    else -> Color.Transparent
                }
                
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(priorityColor, CircleShape)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskCardPreview() {
    TaskCard(
        task = Task(
            id = 1,
            title = "Morning Workout",
            time = "07:30",
            priority = Priority.HIGH
        ),
        onComplete = {}
    )
}

@Preview(showBackground = true)
@Composable
fun TaskCardCompletedPreview() {
    TaskCard(
        task = Task(
            id = 2,
            title = "Read 20 pages",
            time = "09:00",
            priority = Priority.MEDIUM,
            isCompleted = true
        ),
        onComplete = {}
    )
}
