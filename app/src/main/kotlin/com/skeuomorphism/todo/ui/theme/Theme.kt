package com.skeuomorphism.todo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = MutedBlue,
    primaryContainer = SurfaceRaised,
    secondary = MutedGreen,
    secondaryContainer = SurfaceRaised,
    tertiary = MutedRed,
    tertiaryContainer = SurfaceRaised,
    background = BackgroundDark,
    surface = SurfaceDefault,
    surfaceVariant = SurfaceRaised,
    surfaceTint = MutedBlue,
    onPrimary = White,
    onSecondary = White,
    onTertiary = White,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,
    outline = DarkGray,
    outlineVariant = MediumDarkGray,
    error = MutedRed,
    onError = White,
    errorContainer = SurfaceRaised,
    onErrorContainer = MutedRed
)

private val LightColorScheme = lightColorScheme(
    primary = MutedBlue,
    primaryContainer = SurfaceRaisedLight,
    secondary = MutedGreen,
    secondaryContainer = SurfaceRaisedLight,
    tertiary = MutedRed,
    tertiaryContainer = SurfaceRaisedLight,
    background = BackgroundLight,
    surface = SurfaceDefaultLight,
    surfaceVariant = SurfaceRaisedLight,
    surfaceTint = MutedBlue,
    onPrimary = TextPrimaryLight,
    onSecondary = TextPrimaryLight,
    onTertiary = TextPrimaryLight,
    onBackground = TextPrimaryLight,
    onSurface = TextPrimaryLight,
    onSurfaceVariant = TextSecondaryLight,
    outline = DividerLight,
    outlineVariant = MediumGray,
    error = MutedRed,
    onError = White,
    errorContainer = SurfaceRaisedLight,
    onErrorContainer = MutedRed
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
