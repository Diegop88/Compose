package dev.diegop88.compose.layouts

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import dev.diegop88.compose.navigation.NavDestination

object Shapes : NavDestination {
    override val route = "Shapes"
}

@Preview
@Composable
fun Shapes() {
    val myShape = RectangleShape
    Text(
        text = "Testing",
        modifier = Modifier.background(Color.White)
    )
}
