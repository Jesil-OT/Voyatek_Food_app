package com.assessment.voyatek.features.add_food.presentation.model

import android.net.Uri
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.assessment.voyatek.core.domain.util.NetworkError
import com.assessment.voyatek.features.add_food.domian.AddFood
import com.assessment.voyatek.features.add_food.domian.Tags

data class AddFoodUI(
    val imagesUrl: SnapshotStateList<Uri> = SnapshotStateList(),
    val foodName: String = "",
    val foodDescription: String = "",
    val foodCategory: String = "",
    val foodCalories: String = "",
    val tags: List<TagUI> = emptyList(),
    val selectedTags: SnapshotStateList<TagUI> = SnapshotStateList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class TagUI(
    val id: Int? = null,
    val name: String = ""
)

fun Tags.toTagUI() = TagUI(
    id = id,
    name = name
)

//fun AddFood.toAddFoodUI() = AddFoodUI(
//    tags = tags.map { tag -> tag.toTagUI() }
//)
