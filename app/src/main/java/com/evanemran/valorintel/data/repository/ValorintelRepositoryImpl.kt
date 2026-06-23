package com.evanemran.valorintel.data.repository

import com.evanemran.valorintel.data.remote.dto.toDomain
import com.evanemran.valorintel.data.remote.services.ValorintelApiService
import com.evanemran.valorintel.domain.models.Agent
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class ValorintelRepositoryImpl(private val api: ValorintelApiService) : ValorintelRepository {
    override suspend fun getAgents(isPlayableCharacter: Boolean): List<Agent> {
        val response =api.fetchAgents(isPlayableCharacter = isPlayableCharacter)
        return response.data?.map { it!!.toDomain() } ?: emptyList()
    }
}