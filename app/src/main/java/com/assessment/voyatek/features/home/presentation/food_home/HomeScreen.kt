package com.assessment.voyatek.features.home.presentation.food_home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assessment.voyatek.R
import com.assessment.voyatek.core.presentation.components.ErrorBox
import com.assessment.voyatek.core.presentation.components.LoadingBox
import com.assessment.voyatek.core.presentation.util.ObserveAsEvents
import com.assessment.voyatek.core.presentation.util.toLocalizedString
import com.assessment.voyatek.features.home.presentation.food_home.components.ChipGroup
import com.assessment.voyatek.features.home.presentation.food_home.components.FoodListItem
import com.assessment.voyatek.features.home.presentation.food_home.components.HomeSearch
import com.assessment.voyatek.features.home.presentation.food_home.components.HomeTopBar
import com.assessment.voyatek.features.theme.VoyatekTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    homeState: HomeState,
    onAction: (HomeScreenAction) -> Unit,
    onRefresh: (HomeScreenAction) -> Unit,
    onErrorEvent: Flow<HomeScreenEvent>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = homeState.isLoading)
    val background =
        if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color(0xFFFFFFFF)
    var showErrorScreen by remember { mutableStateOf(false) }

    ObserveAsEvents(
        events = onErrorEvent,
        onEvent = { event ->
            when (event) {
                is HomeScreenEvent.Error -> {
                    Toast.makeText(
                        context,
                        event.message toLocalizedString context,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    showErrorScreen = true
                }
            }
        }
    )

    if (showErrorScreen && homeState.error != null) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { onRefresh(HomeScreenAction.OnRefresh) },
            content = {
                ErrorBox(
                    modifier = modifier
                        .fillMaxSize()
                        .background(background),
                    errorMessage = homeState.error toLocalizedString context
                )
            }
        )
    }

    if (homeState.isLoading) {
        LoadingBox(modifier.fillMaxSize())
    } else {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { onRefresh(HomeScreenAction.OnRefresh) },
            content = {
                if (homeState.allFood.isNotEmpty()) {
                    Column(
                        modifier = modifier
                            .background(background)
                            .fillMaxSize(),
                        content = {
                            Spacer(Modifier.padding(top = 16.dp))
                            HomeTopBar(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                username = homeState.name.orEmpty(),
                            )
                            Spacer(Modifier.padding(top = 10.dp))
                            HomeSearch(modifier = Modifier.padding(horizontal = 16.dp))
                            Spacer(Modifier.padding(top = 10.dp))
                            ChipGroup(
                                chipItems = getAllCategories,
                                onItemClick = {}
                            )
                            Spacer(modifier = Modifier.padding(top = 16.dp))
                            HorizontalDivider(
                                thickness = 10.dp,
                                color = Color(0xFFF7F9FC)
                            )
                            LazyColumn(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                content = {
                                    item {
                                        Text(
                                            modifier = Modifier.padding(top = 16.dp),
                                            text = stringResource(R.string.all_foods),
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    }
                                    items(homeState.allFood) { food ->
                                        FoodListItem(foodUI = food, onClick = {})
                                    }
                                }
                            )
                        }
                    )
                }
            }
        )

    }


}


@Preview
@Composable
private fun HomeScreenPreview() {
    VoyatekTheme {
        HomeScreen(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            homeState = HomeState(),
            onAction = {},
            onRefresh = {},
            onErrorEvent = flowOf()
        )
    }
}

val getAllCategories =
    listOf(
        "All",
        "Morning Feast",
        "Sunrise Meal",
        "Dawn Delicacies",
        "Hot meal",
        "Drinks",
        "Healthy",
        "Fast Food",
        "Desserts",
        "Beverages",
        "Vegetarian",
        "Vegan",
        "Seafood",
        "Meat",
        "Poultry",
        "Snacks",
        "Bakery",
        "Dairy",
    )