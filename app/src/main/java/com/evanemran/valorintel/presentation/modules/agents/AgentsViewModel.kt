package com.evanemran.valorintel.presentation.modules.agents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.evanemran.valorintel.di.ServiceLocator
import com.evanemran.valorintel.domain.models.Agent
import com.evanemran.valorintel.domain.usecase.AgentsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class AgentsUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val agents: List<Agent> = emptyList(),
    val error: String? = null
)

class AgentsViewModel(private val agentsUseCase: AgentsUseCase) : ViewModel() {

    private val _states = MutableStateFlow(AgentsUiState(isLoading = true))

    val state: StateFlow<AgentsUiState> = _states.asStateFlow()

    init {
        loadAgents()
    }

    fun loadAgents(forceFresh: Boolean = false) {
        val current = _states.value
        if(!forceFresh && current.agents.isNotEmpty()) return

        _states.update {
            it.copy(
                isLoading =!forceFresh,
                isRefreshing = forceFresh,
                error = null
            )
        }

        viewModelScope.launch {
            try {
                val agents = agentsUseCase(isPlayableCharacter = true)
                _states.update {
                    AgentsUiState(agents = agents)
                }
            }
            catch (e: Exception) {
                _states.update {
                    AgentsUiState(error = e.message)
                }
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                AgentsViewModel(ServiceLocator.agentsUseCase)
            }
        }
    }
}