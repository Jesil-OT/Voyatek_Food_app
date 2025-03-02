package com.assessment.voyatek.features.home.presentation.food_home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assessment.voyatek.core.domain.util.NetworkError
import com.assessment.voyatek.core.domain.util.onError
import com.assessment.voyatek.core.domain.util.onSuccess
import com.assessment.voyatek.features.home.domain.FoodDataSource
import com.assessment.voyatek.features.home.presentation.model.toFoodUI
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    // data source or repository
    private val foodDataSource: FoodDataSource
) : ViewModel() {

    private val _eventChannel = Channel<HomeScreenEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    private val _state = MutableStateFlow(HomeState())
    val state = _state
        .onStart { loadFoodList() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeState()
        )

    fun onAction(action: HomeScreenAction) {
        when (action) {
            is HomeScreenAction.OnFoodClick -> {
//                To do click action message
            }
            is HomeScreenAction.OnRefresh -> loadFoodList()

            is HomeScreenAction.OnSearch -> {
//                To do search action message
            }
        }
    }

    private fun loadFoodList() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            Log.d("homeViewModel", "loadFoodList: Loading....")
            foodDataSource.getFoodList().onSuccess { foods ->
                Log.d("homeViewModel", "loadFoodList: Success")
                _state.update {
                    it.copy(
                        allFood = foods.map { food -> food.toFoodUI() },
                        name = "Lucy",
                        isLoading = false
                    )
                }
            }.onError { eer ->
                Log.d("homeViewModel", "loadFoodList: $eer")
                _state.update {
                    it.copy(
                        error = eer ,
                        isLoading = false,
                        name = "Null"
                    )
                }
                _eventChannel.send(HomeScreenEvent.Error(eer))
            }
        }
    }
}

interface HomeScreenEvent{
    data class Error(val message: NetworkError) : HomeScreenEvent
}