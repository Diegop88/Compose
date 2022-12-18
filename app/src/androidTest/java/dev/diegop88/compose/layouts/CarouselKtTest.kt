package dev.diegop88.compose.layouts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.unit.dp
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit


class CarouselKtTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_Carousel() {
        composeTestRule.setContent {
            LazyCarouselRow(
                items = listOf("Text 1", "Text 2"),
            ) {
                Text(text = it, Modifier.fillMaxWidth())
            }
        }

        composeTestRule.onRoot().printToLog("Carousel")

        Thread.sleep(TimeUnit.SECONDS.toMillis(20))
    }
}
