package com.skeuomorphism.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

data class RecoveryData(
    val day: Int,
    val isOnTrack: Boolean = true,
    val message: String = ""
)

@Composable
fun RecoveryCard(
    recovery: RecoveryData,
    modifier: Modifier = Modifier
) {
    val cardModifier = modifier
        .height(140.dp)
        .fillMaxWidth()
        .shadow(
            elevation = 4.dp,
            shape = CardShape,
            ambientColor = Color.Black.copy(alpha = 0.15f)
        )
        .background(
            color = SurfaceRaised,
            shape = CardShape
        )
        .border(
            width = 1.dp,
            color = DarkGray,
            shape = CardShape
        )
        .clip(CardShape)
    
    Box(
        modifier = cardModifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Day number
            Text(
                text = "DAY ${recovery.day}",
                color = TextPrimary,
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            // Status message
            Text(
                text = "${recovery.day} DAYS STRONG",
                color = TextSecondary,
                fontSize = 14.sp
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Status indicator
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (recovery.isOnTrack) {
                    LedIndicator(
                        color = com.skeuomorphism.todo.ui.theme.MutedGreen,
                        size = 8.dp
                    )
                } else {
                    LedIndicator(
                        color = com.skeuomorphism.todo.ui.theme.MutedRed,
                        size = 8.dp
                    )
                }
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Text(
                    text = if (recovery.isOnTrack) "ON TRACK" else "NEEDS ATTENTION",
                    color = if (recovery.isOnTrack) MutedGreen else Color.Red.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun RecoveryCardSimple(
    day: Int,
    modifier: Modifier = Modifier
) {
    val cardModifier = modifier
        .height(120.dp)
        .fillMaxWidth()
        .shadow(
            elevation = 2.dp,
            shape = CardShape,
            ambientColor = Color.Black.copy(alpha = 0.1f)
        )
        .background(
            color = SurfaceRaised,
            shape = CardShape
        )
        .border(
            width = 1.dp,
            color = DarkGray,
            shape = CardShape
        )
        .clip(CardShape)
    
    Box(
        modifier = cardModifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "DAY $day",
                color = TextPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                LedIndicator(
                    color = com.skeuomorphism.todo.ui.theme.MutedGreen,
                    size = 6.dp
                )
                
                Spacer(modifier = Modifier.width(6.dp))
                
                Text(
                    text = "ON TRACK",
                    color = MutedGreen,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecoveryCardPreview() {
    RecoveryCard(
        recovery = RecoveryData(
            day = 14,
            isOnTrack = true,
            message = "14 DAYS STRONG"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun RecoveryCardSimplePreview() {
    RecoveryCardSimple(day = 14)
}
