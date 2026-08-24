package com.skeuomorphism.todo.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skeuomorphism.todo.ui.theme.MutedBlue
import com.skeuomorphism.todo.ui.theme.SurfaceRaised
import com.skeuomorphism.todo.ui.theme.TextPrimary
import com.skeuomorphism.todo.ui.theme.ToggleHeight
import com.skeuomorphism.todo.ui.theme.ToggleShape
import com.skeuomorphism.todo.ui.theme.ToggleThumbSize
import com.skeuomorphism.todo.ui.theme.ToggleTrackRadius
import com.skeuomorphism.todo.ui.theme.ToggleWidth
import com.skeuomorphism.todo.ui.theme.MediumDarkGray
import kotlinx.coroutines.launch

@Composable
fun SkeuomorphicToggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    enabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    Box(
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier
                .padding(horizontal = 8.dp),
            colors = SwitchDefaults.colors(
                checkedTrackColor = MutedBlue,
                uncheckedTrackColor = MediumDarkGray,
                checkedThumbColor = SurfaceRaised,
                uncheckedThumbColor = SurfaceRaised,
                checkedBorderColor = Color.Transparent,
                uncheckedBorderColor = Color.Transparent
            ),
            shape = ToggleShape,
            thumbContent = null,
            interactionSource = interactionSource,
            enabled = enabled
        )
        
        if (label.isNotEmpty()) {
            Text(
                text = label,
                color = TextPrimary,
                modifier = Modifier
                    .padding(start = ToggleWidth + 16.dp)
                    .align(Alignment.CenterStart)
            )
        }
    }
}

@Composable
fun SkeuomorphicToggleWithLabel(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Box(
        modifier = modifier
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = TextPrimary,
            modifier = Modifier
                .align(Alignment.CenterStart)
        )
        
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(start = 16.dp),
            colors = SwitchDefaults.colors(
                checkedTrackColor = MutedBlue,
                uncheckedTrackColor = MediumDarkGray,
                checkedThumbColor = SurfaceRaised,
                uncheckedThumbColor = SurfaceRaised
            ),
            shape = ToggleShape,
            enabled = enabled
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SkeuomorphicTogglePreview() {
    SkeuomorphicToggle(
        checked = true,
        onCheckedChange = {},
        label = "Dark Mode"
    )
}

@Preview(showBackground = true)
@Composable
fun SkeuomorphicToggleOffPreview() {
    SkeuomorphicToggle(
        checked = false,
        onCheckedChange = {},
        label = "Notifications"
    )
}
