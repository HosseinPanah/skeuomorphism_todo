package com.skeuomorphism.todo.ui.theme

import androidx.compose.ui.graphics.Color

// Primary Monochrome Palette
val NearBlack = Color(0xFF0D0D0D)
val Black = Color(0xFF111111)
val DarkGray = Color(0xFF181818)
val MediumDarkGray = Color(0xFF242424)
val Gray = Color(0xFF3A3A3A)
val MediumGray = Color(0xFF666666)
val LightGray = Color(0xFFA0A0A0)
val SoftGray = Color(0xFFC8C8C8)
val OffWhite = Color(0xFFE8E8E8)
val White = Color(0xFFF2F2F2)

// Accent Colors (LED indicators)
val MutedRed = Color(0xFF8F3F3F)
val MutedRedAlpha = Color(0xFFA94A4A)
val MutedGreen = Color(0xFF4F7056)
val MutedGreenAlpha = Color(0xFF527A5A)
val MutedBlue = Color(0xFF536B80)
val MutedBlueAlpha = Color(0xFF526A82)

// Surface Colors
val SurfaceRaised = Color(0xFF191919)
val SurfaceDefault = Color(0xFF141414)
val SurfaceInset = Color(0xFF0A0A0A)
val SurfaceRaisedLight = Color(0xFFE7E7E7)
val SurfaceDefaultLight = Color(0xFFE2E2E2)
val SurfaceInsetLight = Color(0xFFD5D5D5)

// Text Colors
val TextPrimary = Color(0xFFD8D8D8)
val TextSecondary = Color(0xFF777777)
val TextPrimaryLight = Color(0xFF242424)
val TextSecondaryLight = Color(0xFF666666)

// Background Colors
val BackgroundDark = Color(0xFF0D0D0D)
val BackgroundLight = Color(0xFFDCDCDC)

// Status Colors
val StatusSuccess = MutedGreen
val StatusWarning = MutedRed
val StatusActive = MutedBlue
val StatusInactive = MediumGray

// Progress Colors
val ProgressTrack = MediumDarkGray
val ProgressIndicator = SoftGray
val ProgressComplete = MutedGreen

// Button Colors
val ButtonEnabled = SurfaceRaised
val ButtonPressed = SurfaceInset
val ButtonDisabled = DarkGray

// Navigation Colors
val NavSelected = MutedBlue
val NavUnselected = MediumGray
val NavBackground = NearBlack

// Divider Colors
val Divider = DarkGray
val DividerLight = SoftGray

// Shadow Colors
val ShadowSoft = Color.Black
val ShadowHighlight = Color.White

// Light mode colors
val LightModeColors = mapOf(
    "surface_raised" to SurfaceRaisedLight,
    "surface_default" to SurfaceDefaultLight,
    "surface_inset" to SurfaceInsetLight,
    "text_primary" to TextPrimaryLight,
    "text_secondary" to TextSecondaryLight,
    "background" to BackgroundLight
)

// Dark mode colors (default)
val DarkModeColors = mapOf(
    "surface_raised" to SurfaceRaised,
    "surface_default" to SurfaceDefault,
    "surface_inset" to SurfaceInset,
    "text_primary" to TextPrimary,
    "text_secondary" to TextSecondary,
    "background" to BackgroundDark
)
