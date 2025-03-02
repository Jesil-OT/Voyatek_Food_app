package com.assessment.voyatek.features.home.presentation.food_home

import com.assessment.voyatek.features.home.presentation.model.FoodUI

interface HomeScreenAction {
    data class OnFoodClick(val food: FoodUI): HomeScreenAction
    data object OnRefresh: HomeScreenAction
    data object OnSearch: HomeScreenAction
}