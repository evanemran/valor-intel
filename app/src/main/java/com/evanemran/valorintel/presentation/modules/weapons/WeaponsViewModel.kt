package com.evanemran.valorintel.presentation.modules.weapons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.evanemran.valorintel.di.ServiceLocator
import com.evanemran.valorintel.domain.models.Weapon
import com.evanemran.valorintel.domain.usecase.WeaponsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WeaponsUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val weapons: List<Weapon> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedCategory: String = WeaponCategoryUtil.ALL,
    val error: String? = null,
) {
    val filteredWeapons: List<Weapon>
        get() = WeaponCategoryUtil.filterByCategory(weapons, selectedCategory)
}

class WeaponsViewModel(private val weaponsUseCase: WeaponsUseCase) : ViewModel() {

    private val _state = MutableStateFlow(WeaponsUiState(isLoading = true))
    val state: StateFlow<WeaponsUiState> = _state.asStateFlow()

    init {
        loadWeapons()
    }

    fun loadWeapons(forceFresh: Boolean = false) {
        val current = _state.value
        if (!forceFresh && current.weapons.isNotEmpty()) return

        _state.update {
            it.copy(
                isLoading = !forceFresh,
                isRefreshing = forceFresh,
                error = null,
            )
        }

        viewModelScope.launch {
            try {
                val weapons = weaponsUseCase.getAll()
                    .sortedBy { it.displayName?.lowercase() }
                val categories = WeaponCategoryUtil.extractCategories(weapons)
                _state.update {
                    it.copy(
                        isLoading = false,
                        isRefreshing = false,
                        weapons = weapons,
                        categories = categories,
                        selectedCategory = it.selectedCategory.takeIf { selected ->
                            selected == WeaponCategoryUtil.ALL || categories.contains(selected)
                        } ?: WeaponCategoryUtil.ALL,
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    WeaponsUiState(error = e.message)
                }
            }
        }
    }

    fun selectCategory(category: String) {
        _state.update { it.copy(selectedCategory = category) }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer { WeaponsViewModel(ServiceLocator.weaponsUseCase) }
        }
    }
}
