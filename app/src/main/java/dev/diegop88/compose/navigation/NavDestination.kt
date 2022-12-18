package dev.diegop88.compose.navigation

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
import dev.diegop88.compose.layouts.Shapes
import dev.diegop88.compose.layouts.Tabs
import dev.diegop88.compose.layouts.Temas

interface NavDestination {
    val route: String

    companion object {
        val destinations = listOf(
            Carousel,
            CustomCarousel,
            CustomGrid,
            CustomLayout,
            CustomModifierCarousel,
            Instrinsics,
            Layouts,
            Listas,
            MessageCard,
            OffsetBackground,
            Shapes,
            Tabs,
            Temas,
        )
    }
}
