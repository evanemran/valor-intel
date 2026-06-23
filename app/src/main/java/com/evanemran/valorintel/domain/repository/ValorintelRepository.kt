package com.evanemran.valorintel.domain.repository

import com.evanemran.valorintel.domain.models.Agent

interface ValorintelRepository {
    suspend fun getAgents(isPlayableCharacter: Boolean): List<Agent>
}