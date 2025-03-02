package com.assessment.voyatek.features.add_food.presentation

import android.net.Uri
import com.assessment.voyatek.features.add_food.presentation.model.TagUI

sealed interface AddFoodScreenAction {
    data class OnNameChange(val name: String) : AddFoodScreenAction
    data class OnDescriptionChange(val description: String) : AddFoodScreenAction
    data class OnCategorySelected(val category: String) : AddFoodScreenAction
    data class OnCaloriesChange(val calories: String) : AddFoodScreenAction
    data class OnTagSelected(val tag: TagUI) : AddFoodScreenAction
    data object OnCreateClick : AddFoodScreenAction
    data class OnDeleteTag(val tag: TagUI) : AddFoodScreenAction
    data class OnDeleteImage(val uri: Uri) : AddFoodScreenAction
}