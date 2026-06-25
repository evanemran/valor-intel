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

data class AgentDetailsUiState(
    val isLoading: Boolean = false,
    val agent: Agent? = null,
    val error: String? = null,
)

class AgentDetailsViewModel(
    private val agentUuid: String,
    private val agentsUseCase: AgentsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(AgentDetailsUiState(isLoading = true))
    val state: StateFlow<AgentDetailsUiState> = _state.asStateFlow()

    init {
        loadAgent()
    }

    fun loadAgent() {
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                val agent = agentsUseCase.getById(agentUuid)
                _state.update {
                    AgentDetailsUiState(
                        isLoading = false,
                        agent = agent,
                        error = if (agent == null) "Agent not found." else null,
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    AgentDetailsUiState(isLoading = false, error = e.message)
                }
            }
        }
    }

    companion object {
        fun factory(agentUuid: String) = viewModelFactory {
            initializer {
                AgentDetailsViewModel(
                    agentUuid = agentUuid,
                    agentsUseCase = ServiceLocator.agentsUseCase,
                )
            }
        }
    }
}
