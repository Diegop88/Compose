package dev.diegop88.compose.layouts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dev.diegop88.compose.navigation.NavDestination

object OffsetBackground : NavDestination {
    override val route = "Offset Background"
}

@Composable
fun OffsetBackground(
    modifier: Modifier,
    backgroundPadding: Dp = 8.dp,
    background: @Composable @UiComposable () -> Unit,
    content: @Composable @UiComposable () -> Unit
) {
    SubcomposeLayout(
        modifier = modifier
    ) { constraints ->
        val backgroundOffset = backgroundPadding.roundToPx()

        val measurable = subcompose("content", content).map { it.measure(constraints) }

        val tabWidth =
            measurable.fold(0) { initial, placeable -> initial.coerceAtLeast(placeable.width) }
        val tabHeight =
            measurable.fold(0) { initial, placeable -> initial.coerceAtLeast(placeable.height) }

        val backgroundWidth = tabWidth + (backgroundOffset * 2)
        val backgroundHeight = tabHeight + (backgroundOffset * 2)

        layout(tabWidth, tabHeight) {
            measurable.forEach { it.placeRelative(0, 0) }

            subcompose("background", background).map {
                it.measure(Constraints.fixed(backgroundWidth, backgroundHeight))
                    .placeRelative(-backgroundOffset, -backgroundOffset, -1f)
            }
        }
    }
}

@Composable
fun Background(interactionSource: MutableInteractionSource) {
    val hovered by interactionSource.collectIsHoveredAsState()
    val focussed by interactionSource.collectIsFocusedAsState()
    val pressed by interactionSource.collectIsPressedAsState()

    val backgroundColor = when {
        hovered -> Color.LightGray
        focussed -> Color.LightGray
        pressed -> Color.LightGray
        else -> Color.White
    }

    val borderStroke = when {
        focussed -> BorderStroke(2.dp, Color.Black)
        else -> BorderStroke(0.dp, Color.White)
    }

    val shape = RoundedCornerShape(8.dp)

    Box(
        Modifier
            .background(color = backgroundColor, shape)
            .border(border = borderStroke, shape)
    )
}


@Preview
@Composable
fun Preview_OffsetBackground() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (first, second) = createRefs()
        val interactionSource = remember { MutableInteractionSource() }
        val ripple = rememberRipple(bounded = false, color = MaterialTheme.colors.primary)

        OffsetBackground(
            modifier = Modifier.constrainAs(first) {
                centerTo(parent)
            },
            background = { Background(interactionSource) }
        ) {
            Text(
                text = "Tab content",
                modifier = Modifier.selectable(
                    selected = false,
                    onClick = { },
                    enabled = true,
                    role = Role.Tab,
                    interactionSource = interactionSource,
                    indication = ripple
                )
            )
        }

        Box(modifier = Modifier
            .constrainAs(second) {
                start.linkTo(first.start)
                end.linkTo(first.end)
                top.linkTo(first.bottom, margin = 2.dp)
                width = Dimension.fillToConstraints
            }
            .background(Color.Blue)
            .height(2.dp)
        )
    }
}