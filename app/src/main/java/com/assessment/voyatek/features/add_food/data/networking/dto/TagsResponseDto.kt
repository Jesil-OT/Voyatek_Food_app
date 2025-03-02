package com.assessment.voyatek.features.add_food.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagsResponseDto(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String,
    @SerialName("data") val data: List<TagsDto>
)
