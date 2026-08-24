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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skeuomorphism.todo.ui.theme.CardShape
import com.skeuomorphism.todo.ui.theme.DarkGray
import com.skeuomorphism.todo.ui.theme.MutedBlue
import com.skeuomorphism.todo.ui.theme.MutedGreen
import com.skeuomorphism.todo.ui.theme.SoftGray
import com.skeuomorphism.todo.ui.theme.SurfaceRaised
import com.skeuomorphism.todo.ui.theme.TextPrimary
import com.skeuomorphism.todo.ui.theme.TextSecondary

data class XPData(
    val level: Int,
    val currentXP: Int,
    val maxXP: Int,
    val progress: Float
)

@Composable
fun XPProgressCard(
    xpData: XPData,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth()
            .clip(CardShape)
            .background(SurfaceRaised)
            .border(1.dp, DarkGray, CardShape)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Level indicator
                Text(
                    text = "LEVEL ${xpData.level}",
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.width(16.dp))
                
                // XP text
                Text(
                    text = "${xpData.currentXP} / ${xpData.maxXP} XP",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Progress bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(DarkGray, RoundedCornerShape(2.dp)),
                contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    modifier = Modifier
                        .width((200.dp * xpData.progress).coerceAtMost(200.dp))
                        .height(4.dp)
                        .background(
                            color = MutedGreen,
                            shape = RoundedCornerShape(2.dp)
                        )
                )
            }
            
            // Progress bars (block style)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val filledBars = (xpData.progress * 10).toInt()
                val emptyBars = 10 - filledBars
                
                repeat(filledBars) { index ->
                    Box(
                        modifier = Modifier
                            .width(16.dp)
                            .height(8.dp)
                            .background(MutedGreen, RoundedCornerShape(1.dp))
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                }
                
                repeat(emptyBars) { index ->
                    Box(
                        modifier = Modifier
                            .width(16.dp)
                            .height(8.dp)
                            .background(SoftGray, RoundedCornerShape(1.dp))
                    )
                    if (index < emptyBars - 1) {
                        Spacer(modifier = Modifier.width(2.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun XPProgressSimple(
    level: Int,
    currentXP: Int,
    maxXP: Int,
    modifier: Modifier = Modifier
) {
    val progress = if (maxXP > 0) currentXP.toFloat() / maxXP else 0f
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "LEVEL $level",
            color = TextPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = "$currentXP / $maxXP XP",
            color = TextSecondary,
            fontSize = 12.sp
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Progress bars
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val filledBars = (progress * 10).toInt()
            val emptyBars = 10 - filledBars
            
            repeat(filledBars) { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                        .background(MutedGreen, RoundedCornerShape(1.dp))
                )
                if (index < filledBars - 1) {
                    Spacer(modifier = Modifier.width(2.dp))
                }
            }
            
            repeat(emptyBars) { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                        .background(SoftGray, RoundedCornerShape(1.dp))
                )
                if (index < emptyBars - 1) {
                    Spacer(modifier = Modifier.width(2.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun XPProgressCardPreview() {
    XPProgressCard(
        xpData = XPData(
            level = 12,
            currentXP = 1240,
            maxXP = 1500,
            progress = 1240f / 1500f
        )
    )
}

@Preview(showBackground = true)
@Composable
fun XPProgressSimplePreview() {
    XPProgressSimple(
        level = 5,
        currentXP = 350,
        maxXP = 500
    )
}
