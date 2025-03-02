package com.assessment.voyatek.features.add_food.presentation

import com.assessment.voyatek.core.domain.util.NetworkError

sealed interface AddFoodEvents {
    data class Error(val message: NetworkError) : AddFoodEvents
    data object Success : AddFoodEvents
}