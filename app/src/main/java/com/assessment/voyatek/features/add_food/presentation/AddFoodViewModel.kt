package com.assessment.voyatek.features.add_food.presentation

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assessment.voyatek.core.domain.util.onError
import com.assessment.voyatek.core.domain.util.onSuccess
import com.assessment.voyatek.features.add_food.data.networking.dto.TagsDto
import com.assessment.voyatek.features.add_food.domian.AddFoodDataSource
import com.assessment.voyatek.features.add_food.domian.Tags
import com.assessment.voyatek.features.add_food.presentation.model.AddFoodUI
import com.assessment.voyatek.features.add_food.presentation.model.TagUI
import com.assessment.voyatek.features.add_food.presentation.model.toTagUI
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddFoodViewModel(
    // data source or repository
    private val addFoodDataSource: AddFoodDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(AddFoodUI())
    val state = _state.asStateFlow()

    private val _event = Channel<AddFoodEvents>()
    val event = _event.receiveAsFlow()

    val buttonState: StateFlow<Boolean> = state.map {
        it.foodName.isNotBlank() && it.foodDescription.isNotBlank()
                && it.foodCategory.isNotBlank() && it.foodCalories.isNotBlank()
                && it.selectedTags.isNotEmpty() && it.imagesUrl.isNotEmpty()

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    init {
        getTags()
    }

    fun onAction(action: AddFoodScreenAction) {
        when (action) {
            is AddFoodScreenAction.OnCaloriesChange -> {
                setCalories(action.calories)
            }

            is AddFoodScreenAction.OnCategorySelected -> {
                setCategory(action.category)
            }

            is AddFoodScreenAction.OnDescriptionChange -> {
                setDescription(action.description)
            }

            is AddFoodScreenAction.OnNameChange -> {
                setName(action.name)
            }

            is AddFoodScreenAction.OnTagSelected -> {
                setTag(action.tag)
            }

            is AddFoodScreenAction.OnCreateClick -> {
                onAddClick()
            }

            is AddFoodScreenAction.OnDeleteTag -> {
                deleteTag(action.tag)
            }

            is AddFoodScreenAction.OnDeleteImage -> {
                deleteUri(action.uri)
            }
        }
    }

    private fun onAddClick() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            addFoodDataSource.addFood(
                image = state.value.imagesUrl,
                name = state.value.foodName,
                description = state.value.foodDescription,
                categoryId = (1..10).random(),
                category = state.value.foodCategory,
                calories = state.value.foodCalories.toInt(),
                tags = state.value.selectedTags.map { it.id.toString() }
            ).onSuccess { foods ->
                //it returns food
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
                Log.d("AddFoodViewModel", "success!!")
                _event.send(AddFoodEvents.Success)

            }.onError { err ->
                _state.update {
                    it.copy(
                        error = err.name,
                        isLoading = false
                    )
                }
                _event.send(AddFoodEvents.Error(err))
                Log.d("AddFoodViewModel", "error message ${err.name}")
            }
        }
    }

    private fun getTags() {
        viewModelScope.launch {
            addFoodDataSource.getTags().onSuccess { tags ->
                _state.update {
                    it.copy(
                        tags = tags.map { tags -> tags.toTagUI() }
                    )
                }
            }.onError { err ->
                _state.update {
                    it.copy(
                        error = err.name
                    )
                }
                _event.send(AddFoodEvents.Error(err))
            }
        }
    }

    private fun setName(name: String) {
        _state.update { it.copy(foodName = name) }
    }

    private fun setDescription(description: String) {
        _state.update { it.copy(foodDescription = description) }
    }

    private fun setCategory(category: String) {
        _state.update { it.copy(foodCategory = category) }
    }

    private fun setCalories(calories: String) {
        _state.update { it.copy(foodCalories = calories) }
    }

    private fun setTag(tag: TagUI) {
        val currentTags: SnapshotStateList<TagUI> = SnapshotStateList()
        currentTags.addAll(state.value.selectedTags)
        // for checking if the tag has been entered already
        if (!currentTags.contains(tag)) {
            currentTags.add(tag)
            _state.update {
                it.copy(selectedTags = currentTags)
            }
        }
    }

    private fun deleteTag(tag: TagUI) {
        val currentTags: SnapshotStateList<TagUI> = SnapshotStateList()
        currentTags.addAll(state.value.selectedTags)
        _state.update {
            currentTags.remove(tag)
            it.copy(selectedTags = currentTags)
        }
    }

    fun setImageUris(uris: List<Uri>) {
        val currentUris: SnapshotStateList<Uri> = SnapshotStateList()
        currentUris.addAll(state.value.imagesUrl)
        val urisToAdd = uris.filter { it !in currentUris }
        currentUris.addAll(urisToAdd)
        _state.update {
            it.copy(imagesUrl = currentUris)
        }
    }

    private fun deleteUri(uri: Uri) {
        val currentUris: SnapshotStateList<Uri> = SnapshotStateList()
        currentUris.addAll(state.value.imagesUrl)
        _state.update {
            currentUris.remove(uri)
            it.copy(imagesUrl = currentUris)
        }
    }
}