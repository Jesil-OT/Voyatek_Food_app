package com.assessment.voyatek.features.add_food.domian

import android.net.Uri
import com.assessment.voyatek.core.domain.util.NetworkError
import com.assessment.voyatek.core.domain.util.Result
import com.assessment.voyatek.features.home.domain.Food

interface AddFoodDataSource {
    suspend fun addFood(
        image: List<Uri>,
        name: String,
        description: String,
        categoryId: Int,
        calories: Int,
        category: String,
        tags: List<String>
    ): Result<Food, NetworkError>

    suspend fun getTags(): Result<List<Tags>, NetworkError>
}
