package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.Agent
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class AgentsUseCase(private val repository: ValorintelRepository) {
    suspend operator fun invoke(isPlayableCharacter: Boolean): List<Agent> = repository.getAgents(isPlayableCharacter)
}