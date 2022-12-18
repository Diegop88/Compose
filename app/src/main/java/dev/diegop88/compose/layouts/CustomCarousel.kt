package dev.diegop88.compose.layouts

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import dev.diegop88.compose.navigation.NavDestination

object CustomCarousel : NavDestination {
    override val route = "Custom Carousel"
}

@Composable
fun CustomCarousel(
    modifier: Modifier = Modifier,
    spacing: Dp = 16.dp,
    horizontalPadding: Dp = 24.dp,
    content: @Composable () -> Unit
) {
    val measurePolicy = remember(spacing, horizontalPadding) {
        CustomCarouselPolicy(spacing, horizontalPadding)
    }
    Layout(
        content = content,
        modifier = modifier.horizontalScroll(rememberScrollState()),
        measurePolicy = measurePolicy
    )
}

@Preview
@Composable
fun PreviewCustomCarousel() {
    CustomCarousel {
        repeat(10) {
            Box(modifier = Modifier.size(width = 40.dp, height = 20.dp))
        }
    }
}

private fun Modifier.parentConstraints(
    result: (Constraints) -> Unit
) = this.then(layout { measurable, constraints ->
    result(constraints)
    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
        placeable.placeRelative(0, 0)
    }
})

private class CustomCarouselPolicy(
    private val spacing: Dp,
    private val horizontalPadding: Dp
) : MeasurePolicy {
    override fun MeasureScope.measure(
        measurables: List<Measurable>,
        constraints: Constraints
    ): MeasureResult {
        val carouselConstraints = constraints.copy()
        val placeables = measurables.map { it.measure(carouselConstraints) }
        val carouselSize =
            placeables.fold(IntSize.Zero) { currentMax: IntSize, placeable: Placeable ->
                IntSize(
                    width = currentMax.width + placeable.width,
                    height = currentMax.height.coerceAtLeast(placeable.height)
                )
            }
        return layout(carouselSize.width, carouselSize.height) {
            placeables.map {
                it.placeRelative(0, 0)
            }
        }
    }
}
