package com.assessment.voyatek.features.add_food.domian

import android.net.Uri

data class AddFood(
    val categoryId: Int,
    val image: List<Uri>,
    val name: String,
    val description: String,
    val category: String,
    val calories: Int,
    val tags: List<Tags>,
)
