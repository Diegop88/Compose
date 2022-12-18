package dev.diegop88.compose.layouts

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.diegop88.compose.navigation.NavDestination

object Tabs : NavDestination {
    override val route = "Tabs"
}

@Composable
fun CustomTabs(tabs: List<String>) {
    var selectedIndex by remember { mutableStateOf(0) }
    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        edgePadding = 0.dp,
    ) {
        tabs.forEachIndexed { tabIndex, title ->
            val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

            Tab(
                selected = false,
                interactionSource = interactionSource,
                onClick = { selectedIndex = tabIndex }
            ) {
                val focused by interactionSource.collectIsFocusedAsState()
                val pressed by interactionSource.collectIsPressedAsState()

                val color = when {
                    focused -> Color.Green
                    pressed -> Color.Blue
                    else -> Color.White
                }

                Surface(color = color) {
                    Text(text = title)
                }
            }
        }
    }
}

private val tabs = listOf("First", "Second tab information", "Third", "Fourth")

@Composable
@Preview
fun Preview_CustomTabs() {
    CustomTabs(tabs)
}
