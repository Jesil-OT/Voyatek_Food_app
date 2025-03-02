package com.assessment.voyatek.features.home.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodImagesDto(
    @SerialName("id") val id: Int,
    @SerialName("image_url") val imageUrl: String,
)
