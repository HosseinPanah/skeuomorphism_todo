package com.skeuomorphism.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skeuomorphism.todo.ui.theme.DarkGray
import com.skeuomorphism.todo.ui.theme.MutedBlue
import com.skeuomorphism.todo.ui.theme.NearBlack
import com.skeuomorphism.todo.ui.theme.SurfaceRaised
import com.skeuomorphism.todo.ui.theme.TextPrimary
import com.skeuomorphism.todo.ui.theme.TextSecondary

enum class NavItem {
    HOME, TASKS, HABITS, RECOVERY, INSIGHTS
}

data class NavTab(
    val item: NavItem,
    val label: String,
    val iconRes: Int,
    val selectedIconRes: Int? = null
)

@Composable
fun SkeuomorphicBottomNavigation(
    selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val navTabs = listOf(
        NavTab(NavItem.HOME, "HOME", android.R.drawable.ic_menu_home),
        NavTab(NavItem.TASKS, "TASKS", android.R.drawable.ic_menu_agenda),
        NavTab(NavItem.HABITS, "HABITS", android.R.drawable.ic_menu_myplaces),
        NavTab(NavItem.RECOVERY, "RECOVERY", android.R.drawable.ic_menu_help),
        NavTab(NavItem.INSIGHTS, "INSIGHTS", android.R.drawable.ic_menu_info_details)
    )
    
    Box(
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth()
            .background(NearBlack)
            .border(1.dp, DarkGray, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navTabs.forEach { tab ->
                val isSelected = tab.item == selectedItem
                
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            color = if (isSelected) SurfaceRaised else Color.Transparent,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = if (isSelected) MutedBlue else Color.Transparent,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // LED indicator for selected item
                        if (isSelected) {
                            LedIndicator(
                                color = MutedBlue,
                                size = 6.dp
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                        }
                        
                        // Icon
                        Icon(
                            painter = painterResource(id = tab.iconRes),
                            contentDescription = tab.label,
                            tint = if (isSelected) TextPrimary else TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                        
                        Spacer(modifier = Modifier.size(4.dp))
                        
                        // Label
                        Text(
                            text = tab.label,
                            color = if (isSelected) TextPrimary else TextSecondary,
                            fontSize = 10.sp,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SkeuomorphicBottomNavigationSimple(
    selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val navTabs = listOf(
        NavTab(NavItem.HOME, "Home", android.R.drawable.ic_menu_home),
        NavTab(NavItem.TASKS, "Tasks", android.R.drawable.ic_menu_agenda),
        NavTab(NavItem.HABITS, "Habits", android.R.drawable.ic_menu_myplaces),
        NavTab(NavItem.RECOVERY, "Recovery", android.R.drawable.ic_menu_help),
        NavTab(NavItem.INSIGHTS, "Insights", android.R.drawable.ic_menu_info_details)
    )
    
    Row(
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth()
            .background(NearBlack)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        navTabs.forEach { tab ->
            val isSelected = tab.item == selectedItem
            
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        color = if (isSelected) SurfaceRaised else Color.Transparent,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = tab.iconRes),
                    contentDescription = tab.label,
                    tint = if (isSelected) MutedBlue else TextSecondary,
                    modifier = Modifier.size(24.dp)
                )
                
                // LED indicator
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(top = 4.dp)
                    ) {
                        LedIndicator(
                            color = MutedBlue,
                            size = 4.dp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    SkeuomorphicBottomNavigation(
        selectedItem = NavItem.HOME,
        onItemSelected = {}
    )
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationTasksPreview() {
    SkeuomorphicBottomNavigation(
        selectedItem = NavItem.TASKS,
        onItemSelected = {}
    )
}
