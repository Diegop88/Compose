package dev.diegop88.compose.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimens(
    val smallPadding: Dp = 8.dp,
    val mediumPadding: Dp = 12.dp,
    val largePadding: Dp = 16.dp,
    val listSpacing: Dp = 8.dp,
) {
    override fun toString(): String {
        return "Dimens(small=$smallPadding, medium=$mediumPadding, large=$largePadding)"
    }
}

internal val LocalDimens = staticCompositionLocalOf { Dimens() }
