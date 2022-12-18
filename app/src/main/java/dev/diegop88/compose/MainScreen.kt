package dev.diegop88.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.diegop88.compose.navigation.NavDestination
import dev.diegop88.compose.ui.theme.ComposeTheme

object MainScreen : NavDestination {
    override val route = "Main"
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    destinations: List<NavDestination> = NavDestination.destinations,
    onScreenClick: (NavDestination) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(ComposeTheme.dimens.listSpacing),
        contentPadding = PaddingValues(ComposeTheme.dimens.listSpacing)
    ) {
        items(destinations) { dest ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = MaterialTheme.colors.primary,
                onClick = { onScreenClick(dest) },
            ) {
                Text(
                    text = dest.route,
                    modifier = Modifier.padding(ComposeTheme.dimens.mediumPadding)
                )
            }
        }
    }
}
