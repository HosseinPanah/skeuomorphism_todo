package com.skeuomorphism.todo.ui.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorizedFiniteAnimationSpec
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween

// Skeuomorphic button press animation
val ButtonPressAnimation: AnimationSpec<Float> = tween(
    durationMillis = 100,
    easing = FastOutSlowInEasing
)

// Button release animation
val ButtonReleaseAnimation: AnimationSpec<Float> = tween(
    durationMillis = 150,
    easing = FastOutSlowInEasing
)

// Toggle switch animation
val ToggleSlideAnimation: AnimationSpec<Float> = tween(
    durationMillis = 200,
    easing = FastOutSlowInEasing
)

// Task completion animation
val TaskCompletionAnimation: AnimationSpec<Float> = tween(
    durationMillis = 300,
    easing = FastOutSlowInEasing
)

// Card appearance animation
val CardAppearAnimation: AnimationSpec<Float> = tween(
    durationMillis = 250,
    easing = FastOutSlowInEasing
)

// LED indicator pulse animation
val LedPulseAnimation: InfiniteRepeatableSpec<Float> = repeatable(
    iterations = InfiniteRepeatableSpec.Infinite,
    animation = tween(
        durationMillis = 1000,
        easing = FastOutSlowInEasing
    ),
    repeatMode = RepeatMode.Reverse
)

// Progress bar animation
val ProgressBarAnimation: AnimationSpec<Float> = tween(
    durationMillis = 500,
    easing = FastOutSlowInEasing
)

// Page transition animation
val PageTransitionAnimation: AnimationSpec<Float> = tween(
    durationMillis = 300,
    easing = FastOutSlowInEasing
)

// Spring animation for physical feel
val SpringAnimation: AnimationSpec<Float> = spring(
    dampingRatio = Spring.DampingRatioMediumBouncy,
    stiffness = Spring.StiffnessLow
)

// Fade in animation
val FadeInAnimation: AnimationSpec<Float> = tween(
    durationMillis = 200,
    easing = LinearEasing
)

// Fade out animation
val FadeOutAnimation: AnimationSpec<Float> = tween(
    durationMillis = 150,
    easing = LinearEasing
)

// Scale animation for emphasis
val ScalePulseAnimation: InfiniteRepeatableSpec<Float> = repeatable(
    iterations = InfiniteRepeatableSpec.Infinite,
    animation = keyframes {
        durationMillis = 1000
        0f at 0 with LinearEasing
        1.05f at 500 with FastOutSlowInEasing
        1f at 1000 with FastOutSlowInEasing
    },
    repeatMode = RepeatMode.Restart
)

// Skeuomorphic press animation
fun createPressAnimation(): VectorizedFiniteAnimationSpec<Float> {
    return keyframes {
        durationMillis = 200
        1f at 0 with LinearEasing
        0.95f at 50 with FastOutSlowInEasing
        0.98f at 100 with FastOutSlowInEasing
        1f at 200 with FastOutSlowInEasing
    }
}

// Skeuomorphic release animation
fun createReleaseAnimation(): VectorizedFiniteAnimationSpec<Float> {
    return keyframes {
        durationMillis = 300
        0.98f at 0 with LinearEasing
        1.02f at 50 with FastOutSlowInEasing
        1f at 300 with FastOutSlowInEasing
    }
}

// Custom easing for physical feel
val PhysicalEasing = FastOutSlowInEasing

// Button click animation
val ButtonClickAnimation: AnimationSpec<Float> = tween(
    durationMillis = 100,
    easing = PhysicalEasing
)

// Card elevation animation
val CardElevationAnimation: AnimationSpec<Float> = tween(
    durationMillis = 200,
    easing = PhysicalEasing
)

// Indicator light animation
val IndicatorLightAnimation: InfiniteRepeatableSpec<Float> = repeatable(
    iterations = InfiniteRepeatableSpec.Infinite,
    animation = tween(
        durationMillis = 800,
        easing = FastOutSlowInEasing
    ),
    repeatMode = RepeatMode.Reverse
)
