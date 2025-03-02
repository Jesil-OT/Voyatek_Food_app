package com.assessment.voyatek.features.home.presentation.food_home

import com.assessment.voyatek.core.domain.util.NetworkError
import com.assessment.voyatek.features.home.presentation.model.FoodUI

data class HomeState(
    val name: String? = null,
//    val filterCategory: List<String>? = emptyList(),
    val allFood: List<FoodUI> = emptyList(),
    val isLoading: Boolean = false,
    val error: NetworkError? = null,
    val selectedFood: FoodUI? = null
)