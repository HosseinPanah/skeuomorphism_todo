package com.skeuomorphism.todo.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skeuomorphism.todo.ui.theme.ButtonShape
import com.skeuomorphism.todo.ui.theme.ButtonEnabled
import com.skeuomorphism.todo.ui.theme.ButtonPressed
import com.skeuomorphism.todo.ui.theme.MutedBlue
import com.skeuomorphism.todo.ui.theme.SurfaceRaised
import com.skeuomorphism.todo.ui.theme.TextPrimary
import kotlinx.coroutines.launch

@Composable
fun SkeuomorphicButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    shape: Shape = ButtonShape,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    content: @Composable () -> Unit = { Text(text) }
) {
    val interactionSource = remember { MutableInteractionSource() }
    val scope = rememberCoroutineScope()
    val scale = remember { Animatable(1f) }
    val offsetY = remember { Animatable(0f) }
    
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    scope.launch {
                        scale.animateTo(0.98f, animationSpec = tween(50))
                        offsetY.animateTo(1f, animationSpec = tween(50))
                    }
                }
                is PressInteraction.Release -> {
                    scope.launch {
                        scale.animateTo(1f, animationSpec = tween(100))
                        offsetY.animateTo(0f, animationSpec = tween(100))
                    }
                }
                is PressInteraction.Cancel -> {
                    scope.launch {
                        scale.animateTo(1f, animationSpec = tween(100))
                        offsetY.animateTo(0f, animationSpec = tween(100))
                    }
                }
            }
        }
    }
    
    Button(
        onClick = onClick,
        modifier = modifier
            .shadow(
                elevation = if (enabled) 2.dp else 0.dp,
                shape = shape,
                ambientColor = Color.Black.copy(alpha = 0.1f),
                spotColor = Color.Black.copy(alpha = 0.1f)
            )
            .defaultMinSize(minWidth = 88.dp, minHeight = 48.dp),
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) SurfaceRaised else ButtonPressed,
            contentColor = TextPrimary,
            disabledContainerColor = ButtonPressed,
            disabledContentColor = TextPrimary.copy(alpha = 0.5f)
        ),
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
fun SkeuomorphicIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    enabled: Boolean = true,
    size: dp = 48.dp
) {
    val interactionSource = remember { MutableInteractionSource() }
    val scope = rememberCoroutineScope()
    val scale = remember { Animatable(1f) }
    
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    scope.launch {
                        scale.animateTo(0.95f, animationSpec = tween(50))
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
    
    Box(
        modifier = modifier
            .padding(8.dp)
            .defaultMinSize(minWidth = size, minHeight = size),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .defaultMinSize(minWidth = size, minHeight = size),
            enabled = enabled,
            shape = ButtonShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (enabled) SurfaceRaised else ButtonPressed,
                contentColor = TextPrimary
            ),
            elevation = null,
            border = null,
            contentPadding = PaddingValues(0.dp),
            interactionSource = interactionSource,
            content = icon
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SkeuomorphicButtonPreview() {
    SkeuomorphicButton(
        onClick = {},
        text = "START 10 MIN"
    )
}

@Preview(showBackground = true)
@Composable
fun SkeuomorphicButtonDisabledPreview() {
    SkeuomorphicButton(
        onClick = {},
        text = "DISABLED",
        enabled = false
    )
}
