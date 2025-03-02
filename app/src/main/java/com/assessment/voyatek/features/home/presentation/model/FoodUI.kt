package com.assessment.voyatek.features.home.presentation.model

import com.assessment.voyatek.features.home.domain.Food

data class FoodUI(
    val id: Int,
    val foodImage: String,
    val foodName: String,
    val foodCalories: String,
    val foodDescription: String,
    val foodTags: List<String>,
    val foodImageList: List<String> = emptyList()
)

fun com.assessment.voyatek.features.home.domain.Food.toFoodUI() = FoodUI(
    id = id,
    foodImage = foodImage,
    foodName = foodName,
    foodCalories = "${foodCalories.toString()} Calories",
    foodDescription = foodDescription,
    foodTags = foodTags,
    foodImageList = foodImageList
)
