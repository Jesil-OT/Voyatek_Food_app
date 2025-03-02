package com.assessment.voyatek.features.home.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodResponseDto(
   @SerialName("status") val status: String,
   @SerialName("message") val message: String,
   @SerialName("data") val foodData: List<FoodDto>
)

@Serializable
data class SingleFoodResponseDto(
   @SerialName("status") val status: String,
   @SerialName("message") val message: String,
   @SerialName("data") val foodData: FoodDto
)
