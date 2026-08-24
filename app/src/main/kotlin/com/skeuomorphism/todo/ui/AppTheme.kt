package com.skeuomorphism.todo.ui

import androidx.compose.runtime.Composable
import com.skeuomorphism.todo.ui.theme.AppTheme

@Composable
fun AppTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
        content = content
    )
}
