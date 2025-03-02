package com.assessment.voyatek.features.home.domain

import com.assessment.voyatek.core.domain.util.NetworkError
import com.assessment.voyatek.core.domain.util.Result

interface FoodDataSource {
    suspend fun getFoodList(): Result<List<Food>, NetworkError>
}