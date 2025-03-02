package com.assessment.voyatek.features.add_food.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagsDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
)