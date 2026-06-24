package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.Agent
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class AgentsUseCase(private val repository: ValorintelRepository) {
    suspend operator fun invoke(isPlayableCharacter: Boolean, language: String? = null): List<Agent> =
        repository.getAgents(isPlayableCharacter, language)

    suspend fun getById(uuid: String, language: String? = null): Agent? =
        repository.getAgent(uuid, language)
}
