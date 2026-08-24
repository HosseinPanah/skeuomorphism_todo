package com.skeuomorphism.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skeuomorphism.todo.ui.theme.LedSize
import com.skeuomorphism.todo.ui.theme.LedSizeSM
import com.skeuomorphism.todo.ui.theme.MutedBlue
import com.skeuomorphism.todo.ui.theme.MutedGreen
import com.skeuomorphism.todo.ui.theme.MutedRed

enum class LedColor {
    RED, GREEN, BLUE
}

enum class LedSizeType {
    SMALL, NORMAL
}

@Composable
fun LedIndicator(
    color: LedColor,
    sizeType: LedSizeType = LedSizeType.NORMAL,
    modifier: Modifier = Modifier
) {
    val ledColor = when (color) {
        LedColor.RED -> MutedRed
        LedColor.GREEN -> MutedGreen
        LedColor.BLUE -> MutedBlue
    }
    
    val ledSize = when (sizeType) {
        LedSizeType.SMALL -> LedSizeSM
        LedSizeType.NORMAL -> LedSize
    }
    
    Box(
        modifier = modifier
            .size(ledSize)
            .background(ledColor, CircleShape)
    )
}

@Composable
fun LedIndicator(
    color: Color,
    size: Dp = LedSize,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .background(color, CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun LedIndicatorPreview() {
    Box(modifier = Modifier.size(100.dp)) {
        LedIndicator(LedColor.RED, modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun LedIndicatorGreenPreview() {
    Box(modifier = Modifier.size(100.dp)) {
        LedIndicator(LedColor.GREEN, modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun LedIndicatorBluePreview() {
    Box(modifier = Modifier.size(100.dp)) {
        LedIndicator(LedColor.BLUE, modifier = Modifier)
    }
}
