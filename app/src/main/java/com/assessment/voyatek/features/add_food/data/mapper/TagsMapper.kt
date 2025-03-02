package com.assessment.voyatek.features.add_food.data.mapper

import com.assessment.voyatek.features.add_food.data.networking.dto.TagsDto
import com.assessment.voyatek.features.add_food.domian.Tags

fun TagsDto.toTags(): Tags {
    return Tags(
        id = id,
        name = name,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
