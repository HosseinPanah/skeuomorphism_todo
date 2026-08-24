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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skeuomorphism.todo.ui.components.LedIndicator
import com.skeuomorphism.todo.ui.components.SkeuomorphicBarChart
import com.skeuomorphism.todo.ui.components.SkeuomorphicCard
import com.skeuomorphism.todo.ui.components.SkeuomorphicLineChart
import com.skeuomorphism.todo.ui.components.SkeuomorphicWeeklyProgress
import com.skeuomorphism.todo.ui.components.XPProgressCard
import com.skeuomorphism.todo.ui.components.XPProgressSimple
import com.skeuomorphism.todo.ui.theme.BackgroundDark
import com.skeuomorphism.todo.ui.theme.MutedBlue
import com.skeuomorphism.todo.ui.theme.MutedGreen
import com.skeuomorphism.todo.ui.theme.MutedRed
import com.skeuomorphism.todo.ui.theme.TextPrimary
import com.skeuomorphism.todo.ui.theme.TextSecondary

@Composable
fun AnalyticsScreen(
    tasksCompleted: Int = 24,
    habitsCompleted: Int = 18,
    recoveryDays: Int = 14,
    urgesResisted: Int = 5,
    level: Int = 12,
    currentXP: Int = 1240,
    maxXP: Int = 1500
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        // Header
        Text(
            text = "INSIGHTS",
            color = TextPrimary,
            fontSize = 24.sp,
            letterSpacing = 0.1.sp
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Your progress over time",
            color = TextSecondary,
            fontSize = 14.sp
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // XP Progress
        XPProgressCard(
            xpData = com.skeuomorphism.todo.ui.components.XPData(
                level = level,
                currentXP = currentXP,
                maxXP = maxXP,
                progress = if (maxXP > 0) currentXP.toFloat() / maxXP else 0f
            )
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Weekly Progress
        SkeuomorphicCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "WEEKLY PROGRESS",
                        color = TextPrimary,
                        fontSize = 12.sp,
                        letterSpacing = 0.1.sp
                    )
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LedIndicator(
                            color = MutedGreen,
                            size = 6.dp
                        )
                        
                        Spacer(modifier = Modifier.size(4.dp))
                        
                        Text(
                            text = "$tasksCompleted tasks",
                            color = TextSecondary,
                            fontSize = 10.sp
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                SkeuomorphicWeeklyProgress(
                    days = listOf(3, 5, 2, 7, 4, 6, 3)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Charts Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SkeuomorphicCard(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LedIndicator(
                            color = MutedGreen,
                            size = 6.dp
                        )
                        
                        Spacer(modifier = Modifier.size(4.dp))
                        
                        Text(
                            text = "TASKS",
                            color = TextPrimary,
                            fontSize = 10.sp,
                            letterSpacing = 0.1.sp
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    SkeuomorphicBarChart(
                        data = listOf(3f, 5f, 2f, 7f, 4f, 6f, 3f),
                        height = 80
                    )
                }
            }
            
            SkeuomorphicCard(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LedIndicator(
                            color = MutedBlue,
                            size = 6.dp
                        )
                        
                        Spacer(modifier = Modifier.size(4.dp))
                        
                        Text(
                            text = "HABITS",
                            color = TextPrimary,
                            fontSize = 10.sp,
                            letterSpacing = 0.1.sp
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    SkeuomorphicLineChart(
                        data = listOf(1f, 2f, 3f, 2f, 4f, 3f, 5f),
                        lineColor = MutedBlue,
                        pointColor = MutedBlue,
                        height = 80
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Stats Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AnalyticsStat(
                label = "TASKS",
                value = "$tasksCompleted",
                indicator = MutedGreen,
                modifier = Modifier.weight(1f)
            )
            
            AnalyticsStat(
                label = "HABITS",
                value = "$habitsCompleted",
                indicator = MutedBlue,
                modifier = Modifier.weight(1f)
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AnalyticsStat(
                label = "RECOVERY",
                value = "$recoveryDays",
                indicator = MutedGreen,
                modifier = Modifier.weight(1f)
            )
            
            AnalyticsStat(
                label = "URGES",
                value = "$urgesResisted",
                indicator = MutedRed,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun AnalyticsStat(
    label: String,
    value: String,
    indicator: Color,
    modifier: Modifier = Modifier
) {
    SkeuomorphicCard(
        modifier = modifier
            .height(80.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                LedIndicator(
                    color = indicator,
                    size = 6.dp
                )
                
                Spacer(modifier = Modifier.size(4.dp))
                
                Text(
                    text = label,
                    color = TextSecondary,
                    fontSize = 10.sp,
                    letterSpacing = 0.1.sp
                )
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = value,
                color = TextPrimary,
                fontSize = 20.sp,
                letterSpacing = 0.1.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnalyticsScreenPreview() {
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

@Preview(showBackground = true)
@Composable
fun AnalyticsScreenEmptyPreview() {
    AnalyticsScreen(
        tasksCompleted = 0,
        habitsCompleted = 0,
        recoveryDays = 0,
        urgesResisted = 0,
        level = 1,
        currentXP = 0,
        maxXP = 500
    )
}
