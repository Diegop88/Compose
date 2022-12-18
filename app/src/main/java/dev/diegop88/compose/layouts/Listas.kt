package dev.diegop88.compose.layouts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.getTextLayoutResult
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import dev.diegop88.compose.navigation.NavDestination
import kotlinx.coroutines.launch

object Listas : NavDestination {
    override val route = "Listas"
}

@Composable
fun ImageList() {
    val listSize = 15
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(topBar = {
        TopBar(listSize) { coroutineScope.launch { scrollState.animateScrollToItem(it) } }
    }) {
        Surface(Modifier.padding(it)) {
            Column {
                LazyColumn(
                    state = scrollState,
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(listSize) {
                        ImageListItem(it, listSize, Modifier.fillParentMaxWidth())
                    }
                }
            }
        }
    }
}

@Composable
private fun TopBar(listSize: Int, scrollToItemListener: (Int) -> Unit) = TopAppBar {
    Row {
        Button(onClick = { scrollToItemListener(0) }) {
            Text("Scroll to top")
        }
        Button(onClick = { scrollToItemListener(listSize - 1) }) {
            Text("Scroll to bottom")
        }
    }
}


@Composable
private fun ImageListItem(index: Int, listSize: Int, modifier: Modifier = Modifier) {
    MaterialTheme {
        Surface(
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(2.dp, color = Color.Black)
        ) {
            Row(modifier = modifier
                .semantics {
                    getTextLayoutResult("$index of $listSize") { true }
                }
                .height(10.dp * index)) {
                Image(
                    painter = rememberImagePainter(data = "https://developer.android.com/images/brand/Android_Robot.png"),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Item index: $index", style = MaterialTheme.typography.subtitle1)
            }
        }
    }
}

@Preview
@Composable
fun PreviewRowLists() {
    ImageList()
}

