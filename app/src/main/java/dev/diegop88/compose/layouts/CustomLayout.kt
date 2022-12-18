package dev.diegop88.compose.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.diegop88.compose.navigation.NavDestination

object CustomLayout : NavDestination {
    override val route = "Custom Layout"
}

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)

    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseLine = placeable[FirstBaseline]

    val placeableY = firstBaselineToTop.roundToPx() - firstBaseLine
    val height = placeable.height + placeableY
    layout(placeable.width, height) {
        placeable.placeRelative(0, placeableY)
    }
})

@Composable
fun CustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        var yPosition = 0

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += placeable.height + 30
            }
        }
    }
}

@Preview
@Composable
fun PreviewCustomLayout() {
    Column {
        Demo1()
        Demo2()
        Demo3()
    }
}

@Preview
@Composable
fun Demo1() {
    Text(text = "Hello Diego!", modifier = Modifier.firstBaselineToTop(32.dp))
}

@Preview
@Composable
fun Demo2() {
    Text(text = "Hello Diego!", modifier = Modifier.padding(top = 32.dp))
}

@Preview
@Composable
fun Demo3() {
    CustomLayout(modifier = Modifier.padding(16.dp)) {
        Text(text = "Item 1")
        Text(text = "Item 2")
        Text(text = "Item 3")
        Text(text = "Item 4")
    }
}
