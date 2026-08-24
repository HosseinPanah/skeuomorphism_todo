package com.skeuomorphism.todo.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

val AppShapes = object {
    val extraSmall = RoundedCornerShape(4.dp)
    val small = RoundedCornerShape(8.dp)
    val medium = RoundedCornerShape(12.dp)
    val large = RoundedCornerShape(16.dp)
    val extraLarge = RoundedCornerShape(24.dp)
    val full = RoundedCornerShape(100.dp)
}

// Skeuomorphic specific shapes
val ToggleShape = RoundedCornerShape(16.dp)
val ButtonShape = RoundedCornerShape(12.dp)
val CardShape = RoundedCornerShape(16.dp)
val ProgressShape = RoundedCornerShape(100.dp)
val LedShape = RoundedCornerShape(100.dp)
