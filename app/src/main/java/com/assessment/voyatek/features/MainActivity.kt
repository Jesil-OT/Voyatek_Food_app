package com.assessment.voyatek.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.assessment.voyatek.R
import com.assessment.voyatek.core.navigation.NavDestinations
import com.assessment.voyatek.core.navigation.VoyatekAppBottomNav
import com.assessment.voyatek.features.add_food.presentation.AddFoodScreen
import com.assessment.voyatek.features.add_food.presentation.AddFoodViewModel
import com.assessment.voyatek.features.favourite.FavouriteScreen
import com.assessment.voyatek.features.generator.GeneratorScreen
import com.assessment.voyatek.features.home.presentation.food_home.HomeScreen
import com.assessment.voyatek.features.home.presentation.food_home.HomeViewModel
import com.assessment.voyatek.features.planner.PlannerScreen
import com.assessment.voyatek.features.theme.VoyatekTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val homeFoodViewModel: HomeViewModel = koinViewModel()
            val addFoodViewModel: AddFoodViewModel = koinViewModel()

            val homeFoodState by homeFoodViewModel.state.collectAsStateWithLifecycle()
            val addFoodState by addFoodViewModel.state.collectAsStateWithLifecycle()
            val buttonState by addFoodViewModel.buttonState.collectAsState()

            val navController = rememberNavController()
            VoyatekTheme {
                val currentBackStack by navController.currentBackStackEntryAsState()


                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentBackStack?.destination?.hierarchy?.any {
                                it.hasRoute(NavDestinations.Home::class) || it.hasRoute(
                                    NavDestinations.Generator::class
                                ) || it.hasRoute(
                                    NavDestinations.Favourite::class
                                ) || it.hasRoute(
                                    NavDestinations.Planner::class
                                )
                            } == true) {
                            VoyatekAppBottomNav(navController = navController)
                        }
                    },
                    floatingActionButton = {
                        if (currentBackStack?.destination?.hierarchy?.any {
                                it.hasRoute(NavDestinations.Home::class)
                            } == true) {
                            FloatingActionButton(
                                onClick = {
                                    navController.navigate(NavDestinations.Add)
                                },
                                containerColor = Color(0xFFF7F9FC),
                                shape = RoundedCornerShape(360.dp),
                                content = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.add_icon),
                                        contentDescription = stringResource(R.string.add_food_item),
                                    )
                                }
                            )
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NavDestinations.Home,
                    ) {
                        composable<NavDestinations.Home> {
                            HomeScreen(
                                modifier = Modifier.padding(innerPadding),
                                homeState = homeFoodState,
                                onAction = homeFoodViewModel::onAction,
                                onRefresh = homeFoodViewModel::onAction,
                                onErrorEvent = homeFoodViewModel.eventChannel
                            )
                        }
                        composable<NavDestinations.Generator> {
                            GeneratorScreen(modifier = Modifier.padding(innerPadding))
                        }
                        composable<NavDestinations.Add> {
                            AddFoodScreen(
                                addFoodState = addFoodState,
                                addFoodButtonState = buttonState,
                                setImageUris = addFoodViewModel::setImageUris,
                                onAction = addFoodViewModel::onAction,
                                events = addFoodViewModel.event,
                                onBackPress = { navController.popBackStack() }
                            )
                        }
                        composable<NavDestinations.Favourite> {
                            FavouriteScreen(modifier = Modifier.padding(innerPadding))
                        }
                        composable<NavDestinations.Planner> {
                            PlannerScreen(modifier = Modifier.padding(innerPadding))
                        }
                    }
                }
            }
        }
    }
}