package dev.diegop88.compose.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import dev.diegop88.compose.navigation.NavDestination

object CustomModifierCarousel : NavDestination {
    override val route = "Custom Modifier Carousel"
}

@Composable
fun NewCarouselAttemp(
    modifier: Modifier = Modifier,
    spacing: Dp = 16.dp,
    peekWidth: Dp = 16.dp,
    horizontalPadding: Dp = 24.dp,
    content: @Composable () -> Unit
) {
    var maxWidth = 0
    SubcomposeLayout(modifier = modifier
        .parentConstraints { width, _ -> maxWidth = width }
        .horizontalScroll(rememberScrollState())
    ) {
        var finalSpacing = 0
        val width = maxWidth.dp.roundToPx()
        val padding = horizontalPadding.roundToPx()
        var placeables = subcompose(0, content).map {
            it.measure(Constraints.fixedWidth(width - (padding * 2)))
        }
        if (placeables.isNotEmpty() && placeables.size > 1) {
            finalSpacing = spacing.roundToPx()
            val placeablesWidth = width - finalSpacing - peekWidth.roundToPx() - padding
            placeables = subcompose(1, content).map {
                it.measure(Constraints.fixedWidth(placeablesWidth))
            }
            val placeablesHeight = placeables.maxOf { it.height }
            placeables = subcompose(2, content).map {
                it.measure(Constraints.fixed(placeablesWidth, placeablesHeight))
            }
        }
        val rowSize = placeables.fold(IntSize.Zero) { currentMax: IntSize, placeable: Placeable ->
            IntSize(
                width = currentMax.width + placeable.width + finalSpacing,
                height = currentMax.height.coerceAtLeast(placeable.height)
            )
        }
        layout(rowSize.width + (padding * 2), rowSize.height) {
            var xPos = padding
            placeables.forEach { placeable: Placeable ->
                placeable.placeRelative(xPos, 0)
                xPos += placeable.width + spacing.roundToPx()
            }
        }
    }

}

@Preview
@Composable
fun PreviewNewCustomCarousel() {
    NewCarouselAttemp {
        repeat(1) {
            Box(modifier = Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .height(100.dp))
        }
    }
}

private fun Modifier.parentConstraints(
    result: (width: Int, height: Int) -> Unit
) = this.then(layout { measurable, constraints ->
    result(constraints.maxWidth, constraints.maxHeight)
    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
        placeable.placeRelative(0, 0)
    }
})

