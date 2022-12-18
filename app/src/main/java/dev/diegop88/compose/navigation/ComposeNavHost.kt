package dev.diegop88.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.diegop88.compose.MainScreen
import dev.diegop88.compose.layouts.Carousel
import dev.diegop88.compose.layouts.CustomCarousel
import dev.diegop88.compose.layouts.CustomGrid
import dev.diegop88.compose.layouts.CustomLayout
import dev.diegop88.compose.layouts.CustomModifierCarousel
import dev.diegop88.compose.layouts.Instrinsics
import dev.diegop88.compose.layouts.Layouts
import dev.diegop88.compose.layouts.Listas
import dev.diegop88.compose.layouts.MessageCard
import dev.diegop88.compose.layouts.OffsetBackground
import dev.diegop88.compose.layouts.PreviewCustomCarousel
import dev.diegop88.compose.layouts.PreviewCustomLayout
import dev.diegop88.compose.layouts.PreviewGrid
import dev.diegop88.compose.layouts.PreviewLayouts
import dev.diegop88.compose.layouts.PreviewMessageCard
import dev.diegop88.compose.layouts.PreviewNewCustomCarousel
import dev.diegop88.compose.layouts.PreviewRowLists
import dev.diegop88.compose.layouts.PreviewTemas
import dev.diegop88.compose.layouts.Preview_Carousel
import dev.diegop88.compose.layouts.Preview_CustomTabs
import dev.diegop88.compose.layouts.Preview_OffsetBackground
import dev.diegop88.compose.layouts.Preview_TwoTexts
import dev.diegop88.compose.layouts.Shapes
import dev.diegop88.compose.layouts.Tabs
import dev.diegop88.compose.layouts.Temas

@Composable
fun ComposeNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreen.route,
        modifier = modifier
    ) {
        composable(MainScreen.route) {
            MainScreen {
                navController.navigate(it.route)
            }
        }
        composable(Carousel.route) {
            Preview_Carousel()
        }
        composable(CustomCarousel.route) {
            PreviewCustomCarousel()
        }
        composable(CustomGrid.route) {
            PreviewGrid()
        }
        composable(CustomLayout.route) {
            PreviewCustomLayout()
        }
        composable(CustomModifierCarousel.route) {
            PreviewNewCustomCarousel()
        }
        composable(Instrinsics.route) {
            Preview_TwoTexts()
        }
        composable(Layouts.route) {
            PreviewLayouts()
        }
        composable(Listas.route) {
            PreviewRowLists()
        }
        composable(MessageCard.route) {
            PreviewMessageCard()
        }
        composable(OffsetBackground.route) {
            Preview_OffsetBackground()
        }
        composable(Shapes.route) {
            Shapes()
        }
        composable(Tabs.route) {
            Preview_CustomTabs()
        }
        composable(Temas.route) {
            PreviewTemas()
        }
    }
}
