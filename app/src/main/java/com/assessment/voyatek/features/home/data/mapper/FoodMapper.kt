package com.assessment.voyatek.features.home.data.mapper

import com.assessment.voyatek.features.home.data.networking.dto.FoodDto
import com.assessment.voyatek.features.home.domain.Food

fun FoodDto.toFood(): Food {
    return Food(
        id = id,
        foodImage = foodImage.first().imageUrl,
        foodName = foodName,
        foodCalories = foodCalories,
        foodDescription = foodDescription,
        foodTags = foodTags,
        foodImageList = foodImage.map { it.imageUrl }
    )
}