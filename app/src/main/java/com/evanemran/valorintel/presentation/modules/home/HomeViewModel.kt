package com.evanemran.valorintel.presentation.modules.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class HomeTab(val label: String) {
    Agents("Agents"),
    Weapons("Weapons"),
//    Discover("Discover"),
    Maps("Maps"),
    Cosmetics("Cosmetics"),
}

data class HomeUiState(
    val selectedTab: HomeTab = HomeTab.Agents,
)

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    fun selectTab(tab: HomeTab) {
        _state.update { it.copy(selectedTab = tab) }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer { HomeViewModel() }
        }
    }
}
