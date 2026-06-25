package com.evanemran.valorintel.presentation.modules.cosmetics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.evanemran.valorintel.di.ServiceLocator
import com.evanemran.valorintel.domain.models.Buddy
import com.evanemran.valorintel.domain.models.PlayerCard
import com.evanemran.valorintel.domain.models.PlayerTitle
import com.evanemran.valorintel.domain.models.Spray
import com.evanemran.valorintel.domain.usecase.BuddiesUseCase
import com.evanemran.valorintel.domain.usecase.PlayerCardsUseCase
import com.evanemran.valorintel.domain.usecase.PlayerTitlesUseCase
import com.evanemran.valorintel.domain.usecase.SpraysUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class CosmeticTab(val label: String) {
    Buddies("Buddies"),
    Sprays("Sprays"),
    PlayerCards("Player Cards"),
    PlayerTitles("Player Titles"),
}

data class CosmeticsUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val selectedTab: CosmeticTab = CosmeticTab.Buddies,
    val buddies: List<Buddy> = emptyList(),
    val sprays: List<Spray> = emptyList(),
    val playerCards: List<PlayerCard> = emptyList(),
    val playerTitles: List<PlayerTitle> = emptyList(),
    val loadedTabs: Set<CosmeticTab> = emptySet(),
    val error: String? = null,
)

class CosmeticsViewModel(
    private val buddiesUseCase: BuddiesUseCase,
    private val spraysUseCase: SpraysUseCase,
    private val playerCardsUseCase: PlayerCardsUseCase,
    private val playerTitlesUseCase: PlayerTitlesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(CosmeticsUiState(isLoading = true))
    val state: StateFlow<CosmeticsUiState> = _state.asStateFlow()

    init {
        loadTab(CosmeticTab.Buddies)
    }

    fun selectTab(tab: CosmeticTab) {
        _state.update { it.copy(selectedTab = tab, error = null) }
        loadTab(tab)
    }

    fun refresh() {
        loadTab(_state.value.selectedTab, forceFresh = true)
    }

    private fun loadTab(tab: CosmeticTab, forceFresh: Boolean = false) {
        val current = _state.value
        if (!forceFresh && tab in current.loadedTabs) return

        _state.update {
            it.copy(
                isLoading = !forceFresh && tab !in it.loadedTabs,
                isRefreshing = forceFresh,
                error = null,
            )
        }

        viewModelScope.launch {
            try {
                when (tab) {
                    CosmeticTab.Buddies -> {
                        val buddies = buddiesUseCase.getAll()
                            .filter { !it.displayName.isNullOrBlank() }
                            .sortedBy { it.displayName?.lowercase() }
                        _state.update {
                            it.copy(
                                isLoading = false,
                                isRefreshing = false,
                                buddies = buddies,
                                loadedTabs = it.loadedTabs + tab,
                            )
                        }
                    }

                    CosmeticTab.Sprays -> {
                        val sprays = spraysUseCase.getAll()
                            .filter { it.isNullSpray != true && !it.displayName.isNullOrBlank() }
                            .sortedBy { it.displayName?.lowercase() }
                        _state.update {
                            it.copy(
                                isLoading = false,
                                isRefreshing = false,
                                sprays = sprays,
                                loadedTabs = it.loadedTabs + tab,
                            )
                        }
                    }

                    CosmeticTab.PlayerCards -> {
                        val cards = playerCardsUseCase.getAll()
                            .filter { !it.displayName.isNullOrBlank() }
                            .sortedBy { it.displayName?.lowercase() }
                        _state.update {
                            it.copy(
                                isLoading = false,
                                isRefreshing = false,
                                playerCards = cards,
                                loadedTabs = it.loadedTabs + tab,
                            )
                        }
                    }

                    CosmeticTab.PlayerTitles -> {
                        val titles = playerTitlesUseCase.getAll()
                            .filter { !it.displayName.isNullOrBlank() }
                            .sortedBy { it.displayName?.lowercase() }
                        _state.update {
                            it.copy(
                                isLoading = false,
                                isRefreshing = false,
                                playerTitles = titles,
                                loadedTabs = it.loadedTabs + tab,
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isRefreshing = false,
                        error = e.message,
                    )
                }
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                CosmeticsViewModel(
                    buddiesUseCase = ServiceLocator.buddiesUseCase,
                    spraysUseCase = ServiceLocator.spraysUseCase,
                    playerCardsUseCase = ServiceLocator.playerCardsUseCase,
                    playerTitlesUseCase = ServiceLocator.playerTitlesUseCase,
                )
            }
        }
    }
}
