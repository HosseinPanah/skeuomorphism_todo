package com.skeuomorphism.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skeuomorphism.todo.ui.theme.CardShape
import com.skeuomorphism.todo.ui.theme.DarkGray
import com.skeuomorphism.todo.ui.theme.SurfaceRaised
import com.skeuomorphism.todo.ui.theme.SurfaceInset

@Composable
fun SkeuomorphicCard(
    modifier: Modifier = Modifier,
    isInset: Boolean = false,
    hasBorder: Boolean = false,
    content: @Composable () -> Unit
) {
    val backgroundColor = if (isInset) SurfaceInset else SurfaceRaised
    val borderColor = if (hasBorder) DarkGray else Color.Transparent
    
    Box(
        modifier = modifier
            .shadow(
                elevation = if (!isInset) 2.dp else 0.dp,
                shape = CardShape,
                ambientColor = Color.Black.copy(alpha = 0.1f),
                spotColor = Color.Black.copy(alpha = 0.1f)
            )
            .background(backgroundColor, CardShape)
            .border(if (hasBorder) 1.dp else 0.dp, borderColor, CardShape)
            .padding(16.dp)
    ) {
        content()
    }
}

@Composable
fun SkeuomorphicCardInset(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(SurfaceInset, CardShape)
            .padding(16.dp)
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun SkeuomorphicCardPreview() {
    SkeuomorphicCard {
        Box(modifier = Modifier.padding(16.dp)) {
            // Content
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SkeuomorphicCardInsetPreview() {
    SkeuomorphicCard(isInset = true) {
        Box(modifier = Modifier.padding(16.dp)) {
            // Content
        }
    }
}
