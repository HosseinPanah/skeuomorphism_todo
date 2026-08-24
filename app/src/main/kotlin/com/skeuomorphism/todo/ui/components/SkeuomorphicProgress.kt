package com.skeuomorphism.todo.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skeuomorphism.todo.ui.theme.MutedGreen
import com.skeuomorphism.todo.ui.theme.ProgressComplete
import com.skeuomorphism.todo.ui.theme.ProgressIndicator
import com.skeuomorphism.todo.ui.theme.ProgressStrokeWidth
import com.skeuomorphism.todo.ui.theme.ProgressTrack
import com.skeuomorphism.todo.ui.theme.ProgressSizeMD
import com.skeuomorphism.todo.ui.theme.ProgressSizeLG
import com.skeuomorphism.todo.ui.theme.SoftGray

@Composable
fun SkeuomorphicCircularProgress(
    progress: Float,
    modifier: Modifier = Modifier,
    size: Dp = ProgressSizeMD,
    strokeWidth: Dp = ProgressStrokeWidth,
    trackColor: Color = ProgressTrack,
    indicatorColor: Color = ProgressIndicator,
    completeColor: Color = ProgressComplete,
    showLed: Boolean = false,
    ledColor: Color = MutedGreen
) {
    val animatedProgress = remember { Animatable(progress) }
    
    LaunchedEffect(progress) {
        animatedProgress.animateTo(
            targetValue = progress,
            animationSpec = tween(durationMillis = 500)
        )
    }
    
    Box(
        modifier = modifier
            .size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val canvasWidth = size.toPx()
            val canvasHeight = size.toPx()
            val strokeWidthPx = strokeWidth.toPx()
            
            // Background track
            drawCircle(
                color = trackColor,
                radius = (canvasWidth - strokeWidthPx) / 2,
                center = center,
                style = Stroke(strokeWidthPx)
            )
            
            // Progress indicator
            drawArc(
                color = if (progress >= 1f) completeColor else indicatorColor,
                startAngle = -90f,
                sweepAngle = 360 * animatedProgress.value,
                useCenter = false,
                topLeft = center - offset(canvasWidth / 2, canvasHeight / 2) + offset(strokeWidthPx / 2, strokeWidthPx / 2),
                size = size - strokeWidth.toSize(),
                style = Stroke(strokeWidthPx)
            )
        }
        
        if (showLed && progress >= 1f) {
            LedIndicator(
                color = ledColor,
                size = 8.dp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun SkeuomorphicLinearProgress(
    progress: Float,
    modifier: Modifier = Modifier,
    height: Dp = 4.dp,
    trackColor: Color = ProgressTrack,
    indicatorColor: Color = ProgressIndicator,
    cornerRadius: Dp = 2.dp
) {
    val animatedProgress = remember { Animatable(progress) }
    
    LaunchedEffect(progress) {
        animatedProgress.animateTo(
            targetValue = progress,
            animationSpec = tween(durationMillis = 300)
        )
    }
    
    Canvas(
        modifier = modifier
            .size(width = 200.dp, height = height)
    ) {
        val heightPx = height.toPx()
        val cornerRadiusPx = cornerRadius.toPx()
        
        // Background track
        drawRoundRect(
            color = trackColor,
            topLeft = center - offset(100.dp.toPx(), 0f),
            size = size.copy(width = 200.dp.toPx(), height = heightPx),
            cornerRadius = cornerRadiusPx
        )
        
        // Progress indicator
        drawRoundRect(
            color = indicatorColor,
            topLeft = center - offset(100.dp.toPx(), 0f),
            size = size.copy(width = 200.dp.toPx() * animatedProgress.value, height = heightPx),
            cornerRadius = cornerRadiusPx
        )
    }
}

@Composable
fun ProgressText(
    progress: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            // Simple text drawing would be better with Text composable
            // This is a placeholder
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SkeuomorphicCircularProgressPreview() {
    SkeuomorphicCircularProgress(
        progress = 0.72f,
        size = 100.dp
    )
}

@Preview(showBackground = true)
@Composable
fun SkeuomorphicCircularProgressCompletePreview() {
    SkeuomorphicCircularProgress(
        progress = 1f,
        size = 100.dp,
        showLed = true
    )
}

@Preview(showBackground = true)
@Composable
fun SkeuomorphicLinearProgressPreview() {
    SkeuomorphicLinearProgress(
        progress = 0.5f
    )
}
