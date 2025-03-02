package com.assessment.voyatek.features.home.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodDto(
    @SerialName("id") val id: Int,
    @SerialName("foodImages") val foodImage: List<FoodImagesDto>,
    @SerialName("name") val foodName: String,
    @SerialName("calories") val foodCalories: Int,
    @SerialName("description") val foodDescription: String,
    @SerialName("foodTags") val foodTags: List<String>,
)
