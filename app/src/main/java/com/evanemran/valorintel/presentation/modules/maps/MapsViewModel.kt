package com.evanemran.valorintel.presentation.modules.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.evanemran.valorintel.di.ServiceLocator
import com.evanemran.valorintel.domain.models.ValorantMap
import com.evanemran.valorintel.domain.usecase.MapsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MapsUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val maps: List<ValorantMap> = emptyList(),
    val error: String? = null,
)

class MapsViewModel(private val mapsUseCase: MapsUseCase) : ViewModel() {

    private val _state = MutableStateFlow(MapsUiState(isLoading = true))
    val state: StateFlow<MapsUiState> = _state.asStateFlow()

    init {
        loadMaps()
    }

    fun loadMaps(forceFresh: Boolean = false) {
        val current = _state.value
        if (!forceFresh && current.maps.isNotEmpty()) return

        _state.update {
            it.copy(
                isLoading = !forceFresh,
                isRefreshing = forceFresh,
                error = null,
            )
        }

        viewModelScope.launch {
            try {
                val maps = mapsUseCase.getAll()
                    .filter { !it.displayName.isNullOrBlank() }
                    .sortedBy { it.displayName?.lowercase() }
                _state.update { MapsUiState(maps = maps) }
            } catch (e: Exception) {
                _state.update { MapsUiState(error = e.message) }
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer { MapsViewModel(ServiceLocator.mapsUseCase) }
        }
    }
}
