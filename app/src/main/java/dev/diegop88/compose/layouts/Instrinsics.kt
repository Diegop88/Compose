package dev.diegop88.compose.layouts

import androidx.compose.foundation.layout.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.diegop88.compose.navigation.NavDestination

object Instrinsics : NavDestination {
    override val route = "Instrinsics"
}

@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Text(text = text1)
        Divider(color = Color.Black, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))
        Text(text = text2)
    }
}

@Preview
@Composable
fun Preview_TwoTexts() {
    TwoTexts(text1 = "Hola", text2 = "Diego")
}
