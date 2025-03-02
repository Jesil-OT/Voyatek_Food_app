package com.assessment.voyatek.features.home.domain

data class Food(
    val id: Int,
    val foodImage: String,
    val foodName: String,
    val foodCalories: Int,
    val foodDescription: String,
    val foodTags: List<String>,
    val foodImageList: List<String>
)