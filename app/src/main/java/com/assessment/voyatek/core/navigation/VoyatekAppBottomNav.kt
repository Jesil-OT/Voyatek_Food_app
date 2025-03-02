package com.assessment.voyatek.core.navigation

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.assessment.voyatek.R

@Composable
fun VoyatekAppBottomNav(navController: NavHostController) {
    val navBackStack by navController.currentBackStackEntryAsState()
    val background =
        if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color(0xFFFFFFFF)

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = background
    ) {
        bottomNavItems.forEach { navItem ->
            val isCurrentItem = (navBackStack?.destination?.hierarchy?.any {
                it.hasRoute(
                    navItem.route::class
                )
            } == true)
            NavigationBarItem(
                selected = isCurrentItem,
                colors = NavigationBarItemDefaults.colors().copy(selectedIndicatorColor = White),
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = false
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (isCurrentItem) ImageVector.vectorResource(navItem.iconSelected) else ImageVector.vectorResource(
                            navItem.iconUnselected
                        ),
                        contentDescription = navItem.label,
                        tint = if (isCurrentItem) Color(0xFF0D6EFD) else Color(0xFF9D9EA1)
                    )
                },
                label = {
                    Text(
                        text = navItem.label,
                        fontWeight = if (isCurrentItem) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (isCurrentItem) Color(0xFF0D6EFD) else Color(0xFF9D9EA1),
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 12.sp
                    )
                }
            )
        }
    }
}

data class BottomNavItem(
    val label: String,
    @DrawableRes val iconSelected: Int,
    @DrawableRes val iconUnselected: Int,
    val route: NavDestinations
)

val bottomNavItems = listOf(
    BottomNavItem(
        label = "Home",
        iconSelected = R.drawable.home_icon,
        iconUnselected = R.drawable.home_icon,
        route = NavDestinations.Home
    ),
    BottomNavItem(
        label = "Generator",
        iconSelected = R.drawable.generator_icon,
        iconUnselected = R.drawable.generator_icon,
        route = NavDestinations.Generator
    ),
    BottomNavItem(
        label = "Add",
        iconSelected = R.drawable.add_icon,
        iconUnselected = R.drawable.add_icon,
        route = NavDestinations.Add
    ),
    BottomNavItem(
        label = "Favourite",
        iconSelected = R.drawable.heart_unlike,
        iconUnselected = R.drawable.heart_unlike,
        route = NavDestinations.Favourite
    ),
    BottomNavItem(
        label = "Planner",
        iconSelected = R.drawable.calendar_icon,
        iconUnselected = R.drawable.calendar_icon,
        route = NavDestinations.Planner
    )
)

@Preview
@Composable
fun FoodAppBottomNavPreview() {
    VoyatekAppBottomNav(navController = rememberNavController())
}