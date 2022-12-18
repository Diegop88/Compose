package dev.diegop88.compose.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import dev.diegop88.compose.navigation.NavDestination

object Carousel: NavDestination {
    override val route = "Carousel"
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun <T> LazyCarouselRow(
    items: List<T>,
    state: LazyListState = rememberLazyListState(),
    spaceBetweenItems: Dp = 10.dp,
    horizontalPadding: Dp = 0.dp,
    itemContent: @Composable (item: T) -> Unit,
) {
    LazyRow(
        state = state,
        flingBehavior = rememberSnapperFlingBehavior(
            lazyListState = state,
            endContentPadding = horizontalPadding
        ),
        horizontalArrangement = Arrangement.spacedBy(spaceBetweenItems),
        contentPadding = PaddingValues(horizontal = horizontalPadding),
    ) {
        items(items) { item ->
            itemContent(item)
        }
    }
}

@Composable
private fun CarouselItem(item: String) {
    ConstraintLayout(modifier = Modifier.width(120.dp)) {
        val (header, body, footer) = createRefs()

        createVerticalChain(header, body, footer, chainStyle = ChainStyle.SpreadInside)

        Text(text = "Header text", modifier = Modifier.constrainAs(header) {
            top.linkTo(parent.top)
        })
        Text(text = "Bottom text", modifier = Modifier.constrainAs(footer) {
            bottom.linkTo(parent.bottom)
        })
        Text(text = item, modifier = Modifier.constrainAs(body) {

        })
    }
}

@Composable
@Preview
fun Preview_Carousel() {
    Surface {
        LazyCarouselRow(
            items = listToTest,
            spaceBetweenItems = 0.dp,
            horizontalPadding = 0.dp,
        ) {
            CarouselItem(item = it)
        }
    }
}

val listToTest = listOf(
    "Long long long long long long long long long long long long long long long long long long long long long long long long long long long long long text",
    "Long long long long long long long long long long long long long text",
    "Long long long long long long long long long long long long long text",
    "Long long long long long long long long long long long long long text",
    "Long long long long long long long long long long long long long text"
)
