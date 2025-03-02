package com.assessment.voyatek.core.navigation
import kotlinx.serialization.Serializable

sealed class NavDestinations {
    @Serializable
    data object Home : NavDestinations()

    @Serializable
    data object Generator : NavDestinations()

    @Serializable
    data object Add : NavDestinations()

    @Serializable
    data object Favourite : NavDestinations()

    @Serializable
    data object Planner : NavDestinations()
}