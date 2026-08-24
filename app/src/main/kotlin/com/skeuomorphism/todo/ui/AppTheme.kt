package com.skeuomorphism.todo.ui

import androidx.compose.runtime.Composable

@Composable
fun SkeuomorphismAppTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    com.skeuomorphism.todo.ui.theme.AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
        content = content
    )
}
