package com.skeuomorphism.todo.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skeuomorphism.todo.ui.theme.DarkGray
import com.skeuomorphism.todo.ui.theme.LightGray
import com.skeuomorphism.todo.ui.theme.MutedBlue
import com.skeuomorphism.todo.ui.theme.MutedGreen
import com.skeuomorphism.todo.ui.theme.MutedRed
import com.skeuomorphism.todo.ui.theme.SoftGray

@Composable
fun SkeuomorphicLineChart(
    data: List<Float>,
    modifier: Modifier = Modifier,
    lineColor: Color = MutedGreen,
    pointColor: Color = MutedGreen,
    gridColor: Color = DarkGray,
    height: Int = 100
) {
    Box(
        modifier = modifier
            .width(200.dp)
            .height(height.dp)
            .padding(8.dp)
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val dataSize = data.size.coerceAtLeast(1)
            val spacing = canvasWidth / (dataSize - 1).coerceAtLeast(1f)
            
            // Draw grid lines
            drawLine(
                color = gridColor,
                start = Offset(0f, canvasHeight),
                end = Offset(canvasWidth, canvasHeight),
                strokeWidth = 1f
            )
            
            // Draw horizontal grid lines
            for (i in 0..4) {
                val y = canvasHeight * (1 - i * 0.2f)
                drawLine(
                    color = gridColor.copy(alpha = 0.3f),
                    start = Offset(0f, y),
                    end = Offset(canvasWidth, y),
                    strokeWidth = 0.5f
                )
            }
            
            // Draw data line
            val path = Path().apply {
                val maxValue = data.maxOrNull() ?: 1f
                data.forEachIndexed { index, value ->
                    val x = index * spacing
                    val y = canvasHeight * (1 - (value / maxValue.coerceAtLeast(1f)))
                    
                    if (index == 0) {
                        moveTo(x, y)
                    } else {
                        lineTo(x, y)
                    }
                }
            }
            
            drawPath(
                path = path,
                color = lineColor,
                style = Stroke(width = 2f)
            )
            
            // Draw data points
            data.forEachIndexed { index, value ->
                val x = index * spacing
                val y = canvasHeight * (1 - (value / (data.maxOrNull() ?: 1f)))
                
                drawCircle(
                    color = pointColor,
                    center = Offset(x, y),
                    radius = 3f
                )
            }
        }
    }
}

@Composable
fun SkeuomorphicBarChart(
    data: List<Float>,
    modifier: Modifier = Modifier,
    barColor: Color = MutedGreen,
    gridColor: Color = DarkGray,
    height: Int = 100
) {
    Box(
        modifier = modifier
            .width(200.dp)
            .height(height.dp)
            .padding(8.dp)
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val dataSize = data.size.coerceAtLeast(1)
            val spacing = canvasWidth / dataSize
            val maxValue = data.maxOrNull() ?: 1f
            
            // Draw grid lines
            drawLine(
                color = gridColor,
                start = Offset(0f, canvasHeight),
                end = Offset(canvasWidth, canvasHeight),
                strokeWidth = 1f
            )
            
            // Draw bars
            data.forEachIndexed { index, value ->
                val barWidth = spacing * 0.6f
                val barHeight = canvasHeight * (value / maxValue)
                val x = index * spacing + (spacing - barWidth) / 2
                val y = canvasHeight - barHeight
                
                drawRect(
                    color = barColor,
                    topLeft = Offset(x, y),
                    size = android.graphics.Size(barWidth, barHeight),
                    cornerRadius = android.graphics.CornerRadius(2f, 2f)
                )
            }
        }
    }
}

@Composable
fun SkeuomorphicWeeklyProgress(
    days: List<Int>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(200.dp)
            .height(60.dp)
            .padding(8.dp)
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val dayCount = days.size.coerceAtLeast(1)
            val spacing = canvasWidth / dayCount
            val maxDays = days.maxOrNull() ?: 1
            
            // Draw day squares
            days.forEachIndexed { index, count ->
                val squareWidth = spacing * 0.7f
                val squareHeight = canvasHeight * 0.8f
                val x = index * spacing + (spacing - squareWidth) / 2
                val y = (canvasHeight - squareHeight) / 2
                
                val color = when {
                    count == 0 -> DarkGray
                    count >= maxDays -> MutedGreen
                    else -> SoftGray
                }
                
                drawRect(
                    color = color,
                    topLeft = Offset(x, y),
                    size = android.graphics.Size(squareWidth, squareHeight),
                    cornerRadius = android.graphics.CornerRadius(2f, 2f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LineChartPreview() {
    SkeuomorphicLineChart(
        data = listOf(1f, 2f, 3f, 2f, 4f, 3f, 5f)
    )
}

@Preview(showBackground = true)
@Composable
fun BarChartPreview() {
    SkeuomorphicBarChart(
        data = listOf(3f, 5f, 2f, 7f, 4f, 6f, 8f)
    )
}

@Preview(showBackground = true)
@Composable
fun WeeklyProgressPreview() {
    SkeuomorphicWeeklyProgress(
        days = listOf(5, 3, 7, 2, 6, 4, 1)
    )
}
